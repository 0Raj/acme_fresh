package com.acme_fresh.repository;

import com.acme_fresh.module.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order,Integer>{
}
