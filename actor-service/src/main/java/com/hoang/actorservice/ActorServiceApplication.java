package com.hoang.actorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.hoang.actorservice.model")
@EnableJpaRepositories(basePackages = "com.hoang.actorservice.repository")
public class ActorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActorServiceApplication.class, args);
    }

}
