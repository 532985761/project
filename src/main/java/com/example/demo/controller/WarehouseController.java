package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.example.demo.domain.Goods;
import com.example.demo.domain.SelectVO;
import com.example.demo.domain.Warehouse;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.WarehouseMapper;

import com.example.demo.service.GoodsService;
import com.example.demo.service.WarehouseService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/ware")
public class WarehouseController {
    @Resource
    WarehouseService warehouseService;
    @Resource
    WarehouseMapper warehouseMapper;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    GoodsMapper goodsMapper;
    @Resource
    GoodsService goodsService;
    @Resource
    UserMapper userMapper;

    /**
     * 获取仓库信息
     * @return
     */
    @GetMapping("/getWarehouse")
    public ResponseEntity getWarehouse(){
        return ResponseEntity.ok(warehouseService.list());
    }
    /**
     * 新增仓库
     */
    @PostMapping("/addWarehouse")
    public ResponseEntity addWarehouse(@RequestBody Warehouse warehouse){
        warehouse.setAreaRemain(warehouse.getArea());
        String userId = stringRedisTemplate.opsForValue().get("userLogin");
        warehouse.setUserId(Integer.valueOf(userId));
        warehouseMapper.insert(warehouse);
        return ResponseEntity.ok("新增");
    }

    /**
     * 删除仓库
     */
    @GetMapping("/deleteWarehouse/{id}")
    public ResponseEntity deleteWarehouse(@PathVariable("id") Integer id){
        return ResponseEntity.ok(warehouseMapper.deleteById(id));
    }

    /**
     * 改变仓库状态
     */
    @GetMapping("/changeWarehouse/{id}")
    public ResponseEntity changeWarehouse(@PathVariable("id") Integer id){
        Warehouse warehouse = warehouseMapper.selectById(id);
        if (warehouse.getStatus() == 0 || warehouse.getStatus()==1) {
            warehouse.setStatus(2);
            warehouseMapper.updateById(warehouse);
            return ResponseEntity.ok(" 修改状态");

        } else if (warehouse.getStatus() == 2){
            warehouse.setStatus(0);
            warehouseMapper.updateById(warehouse);
            return ResponseEntity.ok("修改状态");

        }
        return ResponseEntity.ok("修改状态");
    }

    /**
     * 获取某个仓库下的货物
     */
    @GetMapping("/getGoodsByWareId/{id}")
    public ResponseEntity getGoodsByWareId(@PathVariable("id") Integer id){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("warehouse_id",id);
        return ResponseEntity.ok( goodsService.list(wrapper));
    }
    /**
     * 获取仓库名和仓库id
     */
    @GetMapping("/getWareSelect")
    public ResponseEntity getWareSelect(){

        List<Warehouse> list = warehouseService.query().select("warehouse_id,warehouse_name").list();
        return ResponseEntity.ok(list);
    }

    /**
     * 转移物品
     */
    @RequestMapping("/changeWare/{wareId}")
    public ResponseEntity changeWare(@RequestParam(value = "id[]") Integer[] id,@PathVariable("wareId") Integer wareId){
        if (id.length <= 0){
            //不是200就会在前端提示错误
            return new ResponseEntity("请选择物品", HttpStatus.MULTI_STATUS);
        }
        String str = "";
        for (Integer integer : id) {
            Goods goods = goodsMapper.selectById(integer);
            goods.setWarehouseId(wareId);

            goodsMapper.updateById(goods);
        }

        return ResponseEntity.ok("ok");
    }

    /**
     * 获取仓库名和仓库id通过用户Id
     */
    @GetMapping("/getWareSelectByUserId")
    public ResponseEntity getWareSelectByUserId(){
        String userId = stringRedisTemplate.opsForValue().get("ws:userLogin");
        List<Warehouse> list = warehouseService.query().select("warehouse_id,warehouse_name").
                eq("user_id",userId).list();
        return ResponseEntity.ok(list);
    }
    /**
     * 物品入库
     */
    @RequestMapping("/intoWare/{wareId}")
    public ResponseEntity intoWare(@RequestParam(value = "id[]") Integer[] id,@PathVariable("wareId") Integer wareId){
        if (id.length <= 0){
            return new ResponseEntity("请选择物品", HttpStatus.MULTI_STATUS);
        }
        String str = "";
        for (Integer integer : id) {
            Goods goods = goodsMapper.selectById(integer);
            goods.setWarehouseId(wareId);
            goods.setStatus(1);
            goodsMapper.updateById(goods);
        }

        return ResponseEntity.ok("ok");
    }
    /**
     * 物品申请出库
     */
    @RequestMapping("/outWare/{wareId}")
    public ResponseEntity outWare(@RequestParam(value = "id[]") Integer[] id,@PathVariable("wareId") Integer wareId){
        if (id.length <= 0){
            return new ResponseEntity("请选择物品", HttpStatus.MULTI_STATUS);
        }
        String str = "";
        for (Integer integer : id) {
            Goods goods = goodsMapper.selectById(integer);
            goods.setWarehouseId(wareId);
            goods.setStatus(3);
            goodsMapper.updateById(goods);
        }

        return ResponseEntity.ok("ok");
    }
    /**
     * 用户申请物品出库
     */
    @RequestMapping("/userOutWare/{wareId}")
    public ResponseEntity userOutWare(@RequestParam(value = "id[]") Integer[] id,@PathVariable("wareId") Integer wareId){
        if (id.length <= 0){
            return new ResponseEntity("请选择物品", HttpStatus.MULTI_STATUS);
        }
        String str = "";
        for (Integer integer : id) {
            Goods goods = goodsMapper.selectById(integer);
            goods.setWarehouseId(wareId);
            goods.setStatus(3);
            goodsMapper.updateById(goods);
        }

        return ResponseEntity.ok("ok");
    }

    /**
     * 获取所有仓库以及仓库租借信息
     */
    @GetMapping("/wareRentInfo")
    public ResponseEntity wareRentInfo(){
        List<Map<String,Object>> mapList = new ArrayList<>();
        warehouseService.list().forEach(r->{
            Map<String,Object> map = new HashMap<>();
            map.put("ware",r);
            map.put("user",userMapper.selectById(r.getUserId()));
            mapList.add(map);
        });
        return ResponseEntity.ok(mapList);
    }

    /**
     * 更新仓库信息
     */
    @PostMapping("/rent")
    public ResponseEntity rent(@RequestBody Warehouse warehouse){
        warehouse.setStatus(1);
        System.out.println(warehouse);
        return ResponseEntity.ok(warehouseMapper.updateById(warehouse));
    }

}

