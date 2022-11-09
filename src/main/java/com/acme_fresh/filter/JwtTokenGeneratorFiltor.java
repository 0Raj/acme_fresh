package com.acme_fresh.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acme_fresh.constant.SecurityConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenGeneratorFiltor extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null) {
            Date iatDate=new Date();
            Date expDate=new Date();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(expDate);
            calendar.add(calendar.HOUR, 4);

            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

            String jwt = Jwts.builder().setIssuer("acme-fresh").setSubject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", authentication.getAuthorities())
                    .setIssuedAt(iatDate)
                    .setExpiration(calendar.getTime())
                    .signWith(key).compact();
            response.setHeader(SecurityConstants.JWT_HEADER, jwt);
        }

        filterChain.doFilter(request, response);

    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return  !request.getServletPath().equals("/acme-fresh/login");
    }
}
