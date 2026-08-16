package com.back.p67restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class P67RestapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(P67RestapiApplication.class, args);
    }

}
