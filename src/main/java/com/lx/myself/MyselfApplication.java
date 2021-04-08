package com.lx.myself;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@MapperScan("com.lx.myself.mapper")
@SpringBootApplication
public class MyselfApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyselfApplication.class, args);
    }

}
