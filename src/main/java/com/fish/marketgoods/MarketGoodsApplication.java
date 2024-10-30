package com.fish.marketgoods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*这个是启动程序的主类*/
@MapperScan("com.fish.marketgoods.dao")
@SpringBootApplication
public class MarketGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketGoodsApplication.class, args);
    }

}