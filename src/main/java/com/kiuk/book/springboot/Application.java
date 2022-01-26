package com.kiuk.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Jpd의 Auditing Annotation 적용
//@ComponentScan(basePackages = "com.kiuk.book.springboot.service")
@SpringBootApplication
public class  Application {
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
