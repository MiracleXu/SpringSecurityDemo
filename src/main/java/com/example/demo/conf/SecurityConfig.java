package com.example.demo.conf;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //基于内存的配置验证方式
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("xt")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("VIP1", "VIP2")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder()).withUser("xxtt")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("VIP2", "VIP3");

        // 用户自定义扩展验证
        // auth.userDetailsService(customUserService);
    }

}
