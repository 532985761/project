package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.Message;
import com.example.demo.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
*  
* @description 针对表【message】的数据库操作Service实现
* @createDate 2022-09-09 14:29:16
*/
@Service
public class MessageService extends ServiceImpl<MessageMapper, Message>
   {

}




