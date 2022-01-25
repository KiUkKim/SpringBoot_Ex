package com.kiuk.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.kiuk.book.springboot.service")
@SpringBootApplication
public class  Application {
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
