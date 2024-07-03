package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication // 1. 标识这是一个 Spring Boot 应用
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args); // 2. 启动 Spring 应用
    }

    @RestController // 3. 标识这是一个控制器，其方法将处理 HTTP 请求
    public static class HelloController {

        @GetMapping("/hello") // 4. 定义一个处理 GET 请求的方法
        public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
            return String.format("Hello %s!", name); // 返回问候语
        }
    }
}