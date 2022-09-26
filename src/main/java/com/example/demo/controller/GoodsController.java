package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.domain.Goods;
import com.example.demo.domain.Message;
import com.example.demo.domain.Warehouse;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.WarehouseMapper;
import com.example.demo.service.GoodsService;
import com.example.demo.service.MessageService;
import com.example.demo.service.WarehouseService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    GoodsService goodsService;
    @Resource
    GoodsMapper goodsMapper;
    @Resource
    MessageMapper messageMapper;
    @Resource
    MessageService messageService;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    WarehouseMapper warehouseMapper;
    @Resource
    WarehouseService warehouseService;

    /**
     * 得到所有货物
     */
    @GetMapping("/getGoods")
    public ResponseEntity getGoods(  ){
        return ResponseEntity.ok(goodsService.list());
    }
    /**
     * 新增货物
     */
    @PostMapping("/addGoods")
    public ResponseEntity addGoods(@RequestBody Goods goods){
        return ResponseEntity.ok(goodsMapper.insert(goods));
    }
    /**
     * 修改货物
     */
    @PostMapping("/updateGoods")
    public ResponseEntity  updateGoods(@RequestBody Goods goods){
        return ResponseEntity.ok(goodsMapper.updateById(goods));
    }

    /**
     * 货物入库
     */
    @PostMapping("/goodsToWare/{goodsId}/{wareId}")
    public ResponseEntity  goodsToWare(@PathVariable("goodsId") Integer goodsId,@PathVariable("wareId") Integer wareId){
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setWarehouseId(wareId);
        //通知管理员
        Message message = new Message();
        String userId = stringRedisTemplate.opsForValue().get("ws:userLogin");
        assert userId != null;
        message.setFromId(Integer.valueOf(userId));
        message.setToId(1);
        message.setContent("货物入库了");
        messageMapper.insert(message);
        return ResponseEntity.ok(goodsMapper.updateById(goods));
    }
    /**
     * 货物出库
     */
    @PostMapping("/goodsOutWare/{goodsId}/{wareId}")
    public ResponseEntity  goodsOutWare(@PathVariable("goodsId") Integer goodsId,@PathVariable("wareId") Integer wareId){
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setWarehouseId(null);
        //通知管理员
        Message message = new Message();
        String userId = stringRedisTemplate.opsForValue().get("ws:userLogin");
        assert userId != null;
        message.setFromId(Integer.valueOf(userId));
        message.setToId(1);
        message.setContent("货物出库了");
        messageMapper.insert(message);
        return ResponseEntity.ok(goodsMapper.updateById(goods));
    }

    /**
     * 每个用户对应的货物
     */
    @GetMapping("/getGoodsByUserId/{id}")
    public ResponseEntity getGoodsByUserId(@PathVariable("id") Integer id){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",id);

        List<Map<String,Object>> maps = new ArrayList<>();
        List<Warehouse> list = warehouseMapper.selectList(queryWrapper);
        list.forEach(r->{
            Map map = new HashMap();
            map.put("ware",r);
            map.put("goods",goodsService.query().eq("warehouse_id",r.getWarehouseId()).
                    eq("status",2).list());
            maps.add(map);
        });
        return ResponseEntity.ok( maps);
    }

    /**
     * 得到单个货物
     */
    @GetMapping("/getGoodsById/{id}")
    public ResponseEntity getGoodsById(@PathVariable("id") String id){
        return ResponseEntity.ok(goodsMapper.selectById(id));

    }

    /**
     * 每个用户对应的货物②
     */
    @GetMapping("/getAllGoodsByUserId/{id}")
    public ResponseEntity getAllGoodsByUserId(@PathVariable("id") Integer id){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",id);

        return ResponseEntity.ok( goodsMapper.selectList(queryWrapper));
    }
    /**
     * 待入库货物
     */
    @GetMapping("/waitWare/{id}")
    public ResponseEntity waitWare(@PathVariable("id") Integer id){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",id);
        queryWrapper.eq("status",0);
        return ResponseEntity.ok( goodsMapper.selectList(queryWrapper));
    }
}
