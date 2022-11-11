package com.acme_fresh.controller;

import com.acme_fresh.module.UserDTO;
import com.acme_fresh.module.OrderConfirmation;
import com.acme_fresh.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/acme-fresh/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody UserDTO customerDTO){
        System.out.println("check from cont");
        customerService.registerCustomer(customerDTO);
        return new ResponseEntity<>("Customer added successfully", HttpStatus.OK);    }
    @PostMapping("/order")
    public ResponseEntity<OrderConfirmation> placeOrder(@RequestBody Map<Integer,Integer> productMap){

        return new ResponseEntity<>(customerService.placeOrder(productMap),HttpStatus.OK);
    }

    @DeleteMapping("/order")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer orderID){

        return new ResponseEntity<>("Order removed", HttpStatus.OK);
    }


}
