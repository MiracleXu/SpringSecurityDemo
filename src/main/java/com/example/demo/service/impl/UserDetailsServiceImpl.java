package com.example.demo.service.impl;

import com.example.demo.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * @author Miracle Xu
 * @Description:
 * @create: 2019/12/20 14:17
 * @Copyright: 2019 www.lvmama.com All rights reserved.
 **/

public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //自定义用户存储数据来源，可以是从关系型数据库，非关系性数据库，或者其他地方获取用户数据。
        UserEntity userEntity = new UserEntity("xt", "123", true);

        //还可以在此设置账号的锁定,过期,凭据失效 等参数
        //...

        // 设置 权限,可以是从数据库中查找出来的
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));


        userEntity.setAuthorities(authorities);

        return userEntity;
    }
}
