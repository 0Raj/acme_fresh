package com.acme_fresh.service;

import com.acme_fresh.module.*;

import java.util.List;
import java.util.Map;


public interface CustomerService {

    boolean registerCustomer(UserDTO customerDTO);
    OrderConfirmation placeOrder(Map<Integer,Integer> productMap);

    boolean deleteOrder(Integer orderID);

    List<Product> getAllProduct();

}
