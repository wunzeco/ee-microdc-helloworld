package com.barclaycard.collections;

import com.barclaycard.collections.controllers.HelloController;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class Controllers extends WebMvcConfigurerAdapter {

    @Bean
    public HelloController hello(@Value("${salutation:Yo}") String greeting) {
        return new HelloController(greeting);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }


}
