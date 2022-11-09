package com.acme_fresh.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acme_fresh.constant.SecurityConstants;
import com.acme_fresh.exception.UserAlreadyExistException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenValidatorFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        String jwt = request.getHeader(SecurityConstants.JWT_HEADER);

        if (null != jwt) {
            try {

                SecretKey key = Keys.hmacShaKeyFor(
                        SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));


                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                String username = String.valueOf(claims.get("username"));

                String autorities= String.valueOf( claims.get("authorities"));

                String[] at=autorities.split("[{[=}]]");

                SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(at[2]);


                List<GrantedAuthority> a=new ArrayList<>();
                a.add(simpleGrantedAuthority);

                Authentication auth = new UsernamePasswordAuthenticationToken(username,null,a);

                SecurityContextHolder.getContext().setAuthentication(auth);

            }
            catch (Exception e) {
                throw new UserAlreadyExistException("Invalid Token received!");
            }

        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/acme-fresh/login");
    }
}
