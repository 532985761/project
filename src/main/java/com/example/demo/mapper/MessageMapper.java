package com.example.demo.mapper;

import com.example.demo.domain.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
*  
* @description 针对表【message】的数据库操作Mapper
* @createDate 2022-09-09 14:29:16
* @Entity com.example.demo.domain.Message
*/
public interface MessageMapper extends BaseMapper<Message> {

}




