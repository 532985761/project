package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.Login;
import com.example.demo.domain.RegisterForm;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
*
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-09-09 14:02:21
*/
@Service
public class UserService extends ServiceImpl<UserMapper, User>
    {
        @Resource
        UserMapper userMapper;
        @Resource
        StringRedisTemplate stringRedisTemplate;

        public ResponseEntity login(Login user, HttpServletRequest request) {
            Map<String,Object> map = new HashMap<>();
            map.put("username",user.getUsername());
            map.put("password",user.getPassword());
            List<User> users = userMapper.selectByMap(map);
            if (!(Objects.equals(stringRedisTemplate.opsForValue().get("captcha:" + request.getRemoteAddr() + ":"), user.getCode()))){
                return new ResponseEntity("验证码有误，请重试", HttpStatus.MULTI_STATUS);

            }
            if (users.size()<=0){
                return new ResponseEntity("账号密码有误，请重试",HttpStatus.MULTI_STATUS);
            }
            else {
                stringRedisTemplate.opsForValue().set("ws:userLogin",userMapper.selectByMap(map).get(0).getId().toString());
                    return ResponseEntity.ok(userMapper.selectByMap(map).get(0));
            }
        }


        public ResponseEntity register(RegisterForm registerForm) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("username",registerForm.getUsername());
            User user = userMapper.selectOne(wrapper);
            if ("".equals(registerForm.getUsername().trim()) || "".equals(registerForm.getPass().trim())){
                return new ResponseEntity("请检查注册信息",HttpStatus.MULTI_STATUS);

            }
            if (user == null){
                User registerUser = new User();
                registerUser.setUsername(registerForm.getUsername());
                registerUser.setPassword(registerForm.getPass());
                registerUser.setCreateTime(new Date());
                registerUser.setSex(1);
                registerUser.setStatus(0);

                userMapper.insert(registerUser);
                return ResponseEntity.ok("注册成功");
            }
            else{
                //只要状态码不是200就会报错，出现提示信息
                return new ResponseEntity("用户名重复",HttpStatus.MULTI_STATUS);
            }
        }


}




