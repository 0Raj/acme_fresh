package com.acme_fresh.service;

import com.acme_fresh.module.UserDTO;
import com.acme_fresh.module.ProductDTO;

public interface FarmerService {

    boolean registerFarmer(UserDTO farmerDTO);
    boolean addProduct(ProductDTO productDTO);

    boolean deleteProduct(Integer productID);

    boolean updateProduct(Integer productID,ProductDTO productDTO);


}
