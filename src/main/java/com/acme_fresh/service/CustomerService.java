package com.acme_fresh.service;

import com.acme_fresh.module.Product;
import com.acme_fresh.module.User;

public interface CustomerService {

    boolean registerCustomer(User user);
    boolean placeOrder(Integer productID);

    boolean deleteOrder(Integer orderID);

}
