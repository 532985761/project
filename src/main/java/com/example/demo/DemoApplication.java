package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 */

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        //获取入口SpringBoot类
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        //设置它的属性
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);

    }

}
