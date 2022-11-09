package com.acme_fresh.service;

import com.acme_fresh.module.User;

public class CustomerServiceImpl implements CustomerService{
    @Override
    public boolean registerCustomer(User user) {
        return false;
    }

    @Override
    public boolean placeOrder(Integer productID) {
        return false;
    }

    @Override
    public boolean deleteOrder(Integer orderID) {
        return false;
    }
}
