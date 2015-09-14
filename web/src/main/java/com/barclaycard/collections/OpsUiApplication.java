package com.barclaycard.collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@EnableAutoConfiguration
@Import({Controllers.class})
public class OpsUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpsUiApplication.class, args);
    }
}
