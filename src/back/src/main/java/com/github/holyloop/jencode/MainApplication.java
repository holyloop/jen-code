package com.github.holyloop.jencode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.github.holyloop.jencode.dao.BaseMapper;

@SpringBootApplication
@MapperScan(basePackageClasses = { BaseMapper.class })
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
