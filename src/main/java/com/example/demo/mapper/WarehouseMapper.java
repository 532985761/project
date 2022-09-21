package com.example.demo.mapper;

import com.example.demo.domain.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 张洪志
* @description 针对表【warehouse】的数据库操作Mapper
* @createDate 2022-09-15 15:05:25
* @Entity com.example.demo.domain.Warehouse
*/@Mapper
public interface WarehouseMapper extends BaseMapper<Warehouse> {

}




