package com.example.demo.controller;


import com.example.demo.domain.Login;
import com.example.demo.domain.RegisterForm;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    UserMapper userMapper;
    /**
     * 用户登录
     */
    @PostMapping("login")
    public ResponseEntity login(@RequestBody Login user, HttpServletRequest request){
        return  userService.login(user,request);
    }
    /**
     * 用户注册
     */
    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterForm registerForm){
        return userService.register(registerForm);

    }
    /**
     * 查询所有用户信息
     */
    @GetMapping("getAllUser")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(userService.list());
    }
    /**
     * 修改用户信息
     */
    @PostMapping("updateUser")
    public ResponseEntity updateUser(@RequestBody User user){
        return ResponseEntity.ok(userMapper.updateById(user));
    }
    /**
     * 删除用户
     */
    @GetMapping("deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userMapper.deleteById(id));
    }
    /**
     * 查询某个用户信息
     */
    @GetMapping("/getUserById/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userMapper.selectById(id));

    }

}
