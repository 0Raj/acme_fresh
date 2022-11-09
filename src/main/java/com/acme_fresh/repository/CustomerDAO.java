package com.acme_fresh.repository;

import com.acme_fresh.module.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer,String>{
}
