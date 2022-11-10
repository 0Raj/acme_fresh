package com.acme_fresh.service;

import com.acme_fresh.exception.NoStrongPasswordException;
import com.acme_fresh.exception.ProductNotFound;
import com.acme_fresh.module.*;
import com.acme_fresh.repository.FarmerDAO;
import com.acme_fresh.repository.ProductDAO;
import com.acme_fresh.repository.UserDAO;
import com.acme_fresh.util.GetCurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FarmerServiceImpl implements FarmerService{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FarmerDAO farmerDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private GetCurrentUser getCurrentUser;

    @Override
    public boolean registerFarmer(FarmerDTO farmerDTO) {

        userDAO.save(mapToFarmer(farmerDTO));

        return true;
    }

    @Override
    public boolean addProduct(ProductDTO productDTO) {

        Farmer currentFarmer =  (Farmer)getCurrentUser.findCurrentUser();
        currentFarmer.getProductList().add(mapToProduct(productDTO));

        userDAO.save(currentFarmer);

        return true;
    }

    @Override
    public boolean deleteProduct(Integer productID) {

        Product reqProduct = productDAO.findById(productID).get();

        Farmer currentFarmer = (Farmer) getCurrentUser.findCurrentUser();

        if(reqProduct.getFarmer() == currentFarmer){
            productDAO.delete(reqProduct);
        }else {
            throw new ProductNotFound("The product is not created by you");
        }

        return true;
    }

    @Override
    public boolean updateProduct(Integer productID, ProductDTO productDTO) {

        Optional<Product> productOptional = productDAO.findById(productID);

        Farmer currentFarmer = (Farmer) getCurrentUser.findCurrentUser();

        if(productOptional.isPresent()){

            if(productOptional.get().getFarmer() != currentFarmer){
                throw new ProductNotFound("Product not created by you");
            }
            Product updatedProduct = productOptional.get();
            updatedProduct.setProductName(productDTO.getProductName());
            updatedProduct.setProductPrice(productDTO.getProductPrice());

            if(productDTO.getCategory().toUpperCase().equals("FRUIT")){
                updatedProduct.setCategory(Category.FRUIT);
            }else if(productDTO.getCategory().toUpperCase().equals("VEGETABLE")){
                updatedProduct.setCategory(Category.VEGETABLE);
            }else{
                updatedProduct.setCategory(Category.GREENS);
            }

            updatedProduct.setUploadedDate(LocalDate.now());

            productDAO.save(updatedProduct);
        }else{
            throw new ProductNotFound("Product not found");
        }

        return false;
    }

    private Farmer mapToFarmer(FarmerDTO farmerDTO){

        Farmer farmer = new Farmer();

        farmer.setEmailId(farmerDTO.getEmailId());
        farmer.setAge(farmerDTO.getAge());
        farmer.setName(farmerDTO.getName());
        farmer.setRole("ROLE_FARMER");
        farmer.setMobileNumber(farmerDTO.getMobileNumber());

        if(farmerDTO.getGender().toUpperCase().equals("M")){
            farmer.setGender(Gender.MALE);
        }else  if(farmerDTO.getGender().toUpperCase().equals("F")){
            farmer.setGender(Gender.FEMALE);
        }else {
            farmer.setGender(Gender.OTHERS);
        }

        if(isValidPassword(farmerDTO.getPassword())){
            farmer.setPassword(passwordEncoder.encode(farmerDTO.getPassword()));
        }else{
            throw new NoStrongPasswordException("Password dosen't meet the password criteria");
        }

        return farmer;
    }

    private boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(password);

        return m.matches();
    }

    private Product mapToProduct(ProductDTO productDTO){
        Product product = new Product();
        if(productDTO.getCategory().toUpperCase().equals("FRUIT")){
            product.setCategory(Category.FRUIT);
        }else if(productDTO.getCategory().toUpperCase().equals("VEGETABLE")){
            product.setCategory(Category.VEGETABLE);
        }else{
            product.setCategory(Category.GREENS);
        }

        product.setFarmer((Farmer)getCurrentUser.findCurrentUser());

        product.setUploadedDate(LocalDate.now());

        product.setProductPrice(productDTO.getProductPrice());

        product.setProductName(productDTO.getProductName());

        return product;

    }
}
