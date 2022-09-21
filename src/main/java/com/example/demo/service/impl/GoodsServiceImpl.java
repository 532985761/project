package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.Goods;
import com.example.demo.service.GoodsService;
import com.example.demo.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author 张洪志
* @description 针对表【goods】的数据库操作Service实现
* @createDate 2022-09-21 21:45:33
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

}




