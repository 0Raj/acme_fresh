package com.acme_fresh.service;

import com.acme_fresh.exception.NoStrongPasswordException;
import com.acme_fresh.module.*;
import com.acme_fresh.repository.FarmerDAO;
import com.acme_fresh.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean registerFarmer(FarmerDTO farmerDTO) {

        userDAO.save(mapToFarmer(farmerDTO));

        return true;
    }

    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProduct(Integer productID) {
        return false;
    }

    @Override
    public boolean updateProduct(Integer productID) {
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

    public boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(password);

        return m.matches();
    }
}
