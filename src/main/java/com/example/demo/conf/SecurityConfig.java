package com.example.demo.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.impl.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .anyRequest().authenticated()
                .antMatchers("/index").hasAnyAuthority("ROLE_VIP2")
                .antMatchers("/hello").hasAnyAuthority("ROLE_VIP3")
                .anyRequest().denyAll()
                .and()
                .formLogin().and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //基于内存的配置验证方式
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder()).withUser("xt")
//                .password(new BCryptPasswordEncoder().encode("123"))
//                .roles("VIP1", "VIP2")
//                .and()
//                .passwordEncoder(new BCryptPasswordEncoder()).withUser("xxtt")
//                .password(new BCryptPasswordEncoder().encode("123"))
//                .roles("VIP2", "VIP3");

        // 用户自定义扩展验证
         auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

//    @Bean
//    public MyUserDetailsService myUserDetailsService() {
//        return new MyUserDetailsService();
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myUserDetailsService());
//    }

}
