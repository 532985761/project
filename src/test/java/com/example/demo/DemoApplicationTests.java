package com.example.demo;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void redis() {
        stringRedisTemplate.opsForValue().set("redis:test", "This is redis.");
        System.out.println(stringRedisTemplate.opsForValue().get("redis:test"));
        stringRedisTemplate.delete("redis:test");
        System.out.println(stringRedisTemplate.opsForValue().get("redis:test"));


    }

    @Test
    public void huTool() {
        int a = 1;
//aStr为"1"
        String aStr = Convert.toStr(a);
        System.out.println(aStr);
        long[] b = {1, 2, 3, 4, 5};
//bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
        System.out.println(bStr);


    }

    @Test
    public void huTool1() {
//从4.1.11开始可以这么用
        Object[] a = {"a", "你", "好", "", 1};

        List list = Convert.toList(a);
        System.out.println(list);
        double x = 3.14;


    }

    @Test
    void captch() {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

//图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write("d:/line.png");

//输出code
        Console.log(lineCaptcha.getCode());

//验证图形验证码的有效性，返回boolean值
        System.out.println(lineCaptcha.verify("1234"));

//重新生成验证码
        lineCaptcha.createCode();

        lineCaptcha.write("d:/test/line.png");
//新的验证码
        Console.log(lineCaptcha.getCode());
//验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");
    }
    @Test
     void shenYu(){

    }


    public static void main(String[] args) {

        canshu(1,23,6);
    }

    private static void canshu(Integer g, Integer... i) {
        System.out.println(Arrays.toString(i));
    }
}
