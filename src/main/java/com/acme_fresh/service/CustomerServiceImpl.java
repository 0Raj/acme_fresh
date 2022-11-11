package com.acme_fresh.service;

import com.acme_fresh.exception.NoStrongPasswordException;
import com.acme_fresh.exception.ProductNotFound;
import com.acme_fresh.exception.UserAlreadyExistException;
import com.acme_fresh.module.*;
import com.acme_fresh.repository.OrderDAO;
import com.acme_fresh.repository.ProductDAO;
import com.acme_fresh.repository.UserDAO;
import com.acme_fresh.util.GetCurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private GetCurrentUser getCurrentUser;

    @Override
    public boolean registerCustomer(@Valid UserDTO customerDTO) {
        Optional<User> user = userDAO.findById(customerDTO.getEmailId());
        if(user.isPresent()){
            throw new UserAlreadyExistException("User Already present");
        }

        userDAO.save(mapToCustomer(customerDTO));
        return true;
    }

    @Override
    public OrderConfirmation placeOrder(Map<Integer,Integer> productMap) {

        Customer currentCustomer = (Customer) getCurrentUser.findCurrentUser();

        OrderConfirmation currentOrder = createOrder(productMap);

        return orderDAO.save(currentOrder);
    }

    @Override
    public boolean deleteOrder(Integer orderID) {

        Customer customer = (Customer) getCurrentUser.findCurrentUser();

        Optional<OrderConfirmation> orderConfirmation = orderDAO.findById(orderID);

        if(orderConfirmation.isPresent()){
            OrderConfirmation order = orderConfirmation.get();

            if(order.getCustomer() == customer){
                orderDAO.deleteById(orderID);
            }else{
                throw new ProductNotFound("The product is not created by you");
            }
        }else {
            throw new ProductNotFound("Order not found");
        }


        return false;
    }

    @Override
    public List<Product> getAllProduct() {
        return productDAO.findAll();
    }

    private Customer mapToCustomer(UserDTO customerDTO){

        Customer customer = new Customer();


        customer.setEmailId(customerDTO.getEmailId());
        customer.setAge(customerDTO.getAge());
        customer.setName(customerDTO.getName());
        customer.setRole("ROLE_CUSTOMER");
        customer.setMobileNumber(customerDTO.getMobileNumber());
        customer.setAddress(customerDTO.getAddress());

        if(customerDTO.getGender().toUpperCase().equals("M")){
            customer.setGender(Gender.MALE);
        }else  if(customerDTO.getGender().toUpperCase().equals("F")){
            customer.setGender(Gender.FEMALE);
        }else {
            customer.setGender(Gender.OTHERS);
        }

        if(isValidPassword(customerDTO.getPassword())){
            customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        }else{
            throw new NoStrongPasswordException("Password dosen't meet the password criteria");
        }

        return customer;
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

    private OrderConfirmation createOrder(Map<Integer,Integer> productMap){
        OrderConfirmation order = new OrderConfirmation();

        Customer currentCustomer = (Customer) getCurrentUser.findCurrentUser();
        order.setCustomer(currentCustomer);

        List<Product> productList = order.getProductList();
        Set<Map.Entry<Integer,Integer>> entrySet = productMap.entrySet();

        Double grandTotal = 0.0;

       for(Map.Entry<Integer,Integer> set : entrySet){
           productList.add(productDAO.findById(set.getKey()).get());
           grandTotal += productDAO.findById(set.getKey()).get().getProductPrice()*set.getValue();
       }

       order.setOrderedDate(LocalDate.now());
       order.setTotal(grandTotal);
       order.setOrderStatus(OrderStatus.ACCEPTED);

       userDAO.save(currentCustomer);
       return order;
    }
}
