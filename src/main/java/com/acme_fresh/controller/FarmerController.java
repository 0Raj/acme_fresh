package com.acme_fresh.controller;

import com.acme_fresh.module.FarmerDTO;
import com.acme_fresh.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme-fresh/farmer")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @PostMapping("/register")
    public ResponseEntity<String> addFarmer(@RequestBody FarmerDTO farmerDTO){
        farmerService.registerFarmer(farmerDTO);

        return new ResponseEntity<>("Farmer Added Successfully", HttpStatus.OK);
    }

}
