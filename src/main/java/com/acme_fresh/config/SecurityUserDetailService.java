package com.acme_fresh.config;

import com.acme_fresh.exception.UserNotFoundException;
import com.acme_fresh.module.User;
import com.acme_fresh.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userDAO.findById(username);

        if (user.isPresent()){
            return new SecurityUserDetails(user.get());
        }

        throw new UserNotFoundException("Please enter valid user name");
    }
}
