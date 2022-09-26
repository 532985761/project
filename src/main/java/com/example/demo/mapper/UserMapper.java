package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
*  
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-09-09 14:02:21
* @Entity demo.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

}




