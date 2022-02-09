package com.kiuk.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaAuditing //Jpa의 Auditing Annotation 적용
//@ComponentScan(basePackages = "com.kiuk.book.springboot.service")
@SpringBootApplication
public class  Application {
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
