package com.acme_fresh.repository;

import com.acme_fresh.module.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerDAO extends JpaRepository<Farmer,String>{
}
