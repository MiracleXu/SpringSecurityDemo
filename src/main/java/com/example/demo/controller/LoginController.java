package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.LoginService;

/**
 * @author Miracle Xu
 * @Description:
 * @create: 2019/12/19 15:43
 * @Copyright: 2019 www.lvmama.com All rights reserved.
 **/
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/index")
    public String index() {
        return loginService.index();
    }
}
