package com.acme_fresh.repository;

import com.acme_fresh.module.Farmer;
import com.acme_fresh.module.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,String>{
}
