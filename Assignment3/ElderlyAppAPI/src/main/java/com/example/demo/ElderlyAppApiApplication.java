package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.demo.repository")
public class ElderlyAppApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElderlyAppApiApplication.class, args);
    }
}