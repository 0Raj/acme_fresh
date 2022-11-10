package com.acme_fresh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme-fresh")
public class UserController {

    @GetMapping("/login")
    public ResponseEntity<String> userLogin(){
        return new ResponseEntity<>("Logged In Successfully", HttpStatus.OK);
    }

}
