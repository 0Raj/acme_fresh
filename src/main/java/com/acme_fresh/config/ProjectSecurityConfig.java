package com.acme_fresh.config;

import com.acme_fresh.filter.JwtTokenGeneratorFiltor;
import com.acme_fresh.filter.JwtTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class ProjectSecurityConfig {


    @Bean
    public SecurityFilterChain gmailUserConfig(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JwtTokenGeneratorFiltor(),BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> {
                    try {
                        auth
                                .antMatchers(HttpMethod.GET,"/swagger-ui/**","/acme-fresh/products").permitAll()
                                .antMatchers(HttpMethod.POST,"/acme-fresh/customer/register" , "/acme-fresh/farmer/register").permitAll()
                                .antMatchers("/acme-fresh/login").authenticated()
                                .antMatchers("/acme-fresh/customer/**").hasAnyRole("CUSTOMER")
                                .antMatchers(HttpMethod.POST,"/acme-fresh/farmer/product").hasAnyRole("FARMER")
                                .antMatchers(HttpMethod.DELETE,"/acme-fresh/farmer/product").hasAnyRole("FARMER")
                                .and().csrf().disable()
                                .logout()
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/end");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
