package com.acme_fresh.repository;

import com.acme_fresh.module.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product,Integer>{
}
