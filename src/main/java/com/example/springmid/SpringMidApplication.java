package com.example.springmid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.example.springmid.services")
public class SpringMidApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMidApplication.class, args);
    }

}
