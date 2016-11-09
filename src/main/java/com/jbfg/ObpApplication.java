package com.jbfg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;


@SpringBootApplication
public class ObpApplication {

    @Resource
    APIClient client;

    public static void main(String[] args) {
        SpringApplication.run(ObpApplication.class, args);
    }

}
