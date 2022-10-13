package com.example.demo.controller;

import com.example.demo.domain.Message;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    MessageService messageService;
    @Resource
    UserMapper userMapper;
    /**
     * 获取消息
     */
    @GetMapping("/getMessage")
    public ResponseEntity getMessage(){
        List<Object> list = new ArrayList<>();
        messageService.list().forEach(r->{
            Map<String,Object> map = new HashMap<>();
            map.put("name",userMapper.selectById(r.getFromId()).getUsername());
            map.put("content",r.getContent());
            list.add(map);
        });
        return ResponseEntity.ok(list);
    }
}
