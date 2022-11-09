package com.acme_fresh.service;

import com.acme_fresh.module.FarmerDTO;
import com.acme_fresh.module.Product;
import com.acme_fresh.module.User;

public interface FarmerService {

    boolean registerFarmer(FarmerDTO farmerDTO);
    boolean addProduct(Product product);

    boolean deleteProduct(Integer productID);

    boolean updateProduct(Integer productID);


}
