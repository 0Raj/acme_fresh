package com.acme_fresh.controller;

import com.acme_fresh.module.UserDTO;
import com.acme_fresh.module.ProductDTO;
import com.acme_fresh.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/acme-fresh/farmer")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @PostMapping("/register")
    public ResponseEntity<String> addFarmer(@Valid @RequestBody UserDTO farmerDTO){
        farmerService.registerFarmer(farmerDTO);
        return new ResponseEntity<>("Farmer Added Successfully", HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO){
        farmerService.addProduct(productDTO);
        return new ResponseEntity<>("Product added Successfully",HttpStatus.OK);
    }

    @DeleteMapping("/product/{productID}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productID") Integer productID){
        farmerService.deleteProduct(productID);
        return new ResponseEntity<>("Product Deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/product/{productID}")
    public ResponseEntity<String> updateProduct(@PathVariable("productID") Integer productID,@RequestBody ProductDTO productDTO){
        farmerService.updateProduct(productID,productDTO);
        return new ResponseEntity<>("Product Updated Successfully",HttpStatus.OK);
    }

}
