package com.acme_fresh.repository;

import com.acme_fresh.module.OrderConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<OrderConfirmation,Integer>{
}
