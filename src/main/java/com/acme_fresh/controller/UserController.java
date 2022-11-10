package com.acme_fresh.controller;

import com.acme_fresh.module.Product;
import com.acme_fresh.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/acme-fresh")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    public ResponseEntity<String> userLogin(){
        return new ResponseEntity<>("Logged In Successfully", HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){

        List<Product> productList = customerService.getAllProduct();
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }

}
