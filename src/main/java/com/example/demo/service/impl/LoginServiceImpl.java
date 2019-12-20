package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Miracle Xu
 * @Description:
 * @create: 2019/12/19 15:46
 * @Copyright: 2019 www.lvmama.com All rights reserved.
 **/

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String index() {
        User user = userMapper.findByUserName("xt");
        return user.getUsername();
    }
}
