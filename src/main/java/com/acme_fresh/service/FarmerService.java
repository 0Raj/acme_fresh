package com.acme_fresh.service;

import com.acme_fresh.module.FarmerDTO;
import com.acme_fresh.module.Product;
import com.acme_fresh.module.ProductDTO;
import com.acme_fresh.module.User;

public interface FarmerService {

    boolean registerFarmer(FarmerDTO farmerDTO);
    boolean addProduct(ProductDTO productDTO);

    boolean deleteProduct(Integer productID);

    boolean updateProduct(Integer productID,ProductDTO productDTO);


}
