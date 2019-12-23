package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.Permission;
import com.example.demo.entity.User;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author Miracle Xu
 * @Description:
 * @create: 2019/12/20 14:17
 * @Copyright: 2019 www.lvmama.com All rights reserved.
 **/
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //自定义用户存储数据来源，可以是从关系型数据库，非关系性数据库，或者其他地方获取用户数据。
//        UserEntity userEntity = new UserEntity("xt", new BCryptPasswordEncoder().encode("123"), true);

        User user = userMapper.findByUserName(userName);
        if (user != null) {
            UserEntity userEntity = new UserEntity(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), true);
            List<Permission> permissionList = permissionMapper.findByUserId(user.getId());
            ArrayList<GrantedAuthority> authorities = new ArrayList();
            permissionList.forEach(item -> authorities.add(new SimpleGrantedAuthority(item.getName())));

        //设置账号的锁定,过期,凭据失效 等参数

        // 设置 权限,可以是从数据库中查找出来的
//        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            userEntity.setAuthorities(authorities);
            return userEntity;
        } else {
            throw new UsernameNotFoundException("username not found");
        }

    }
}
