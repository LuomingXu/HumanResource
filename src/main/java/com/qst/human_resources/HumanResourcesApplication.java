package com.qst.human_resources;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qst.human_resources.mapper")
public class HumanResourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanResourcesApplication.class, args);
    }
}
