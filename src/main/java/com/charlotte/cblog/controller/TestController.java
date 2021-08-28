package com.charlotte.cblog.controller;


import com.charlotte.cblog.bean.User;
import com.charlotte.cblog.config.TokenService;
import com.charlotte.cblog.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TokenService tokenService;


    @GetMapping("/test")
    public String test(){
        return "1";
    }
}
