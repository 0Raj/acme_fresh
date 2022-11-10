package com.acme_fresh.util;
import java.util.Optional;

import com.acme_fresh.module.User;
import com.acme_fresh.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;


import lombok.Data;

@Repository
@Data
public class GetCurrentUser {

    private Object principal;

    @Autowired
    private UserDAO userDao;

    public User findCurrentUser() {
        principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        String username = (String)principal;


        Optional<User> currentUser = userDao.findById(username);

        return currentUser.get();
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }
}