package com.barclaycard.collections;

import com.barclaycard.collections.controllers.ExceptionController;
import com.barclaycard.collections.controllers.HelloController;
import com.equalexperts.logging.OpsLogger;
import com.equalexperts.logging.OpsLoggerFactory;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.nio.file.Paths;

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

    @Bean
    public OpsLogger<LogMessages> logger(@Value("${opslog.location:.}") String logDir) throws IOException {
        return new OpsLoggerFactory().setPath(Paths.get(logDir, "opslog.log")).build();
    }

    @Bean
    public ExceptionController exceptionController(OpsLogger<LogMessages> logger){
        return new ExceptionController(logger);
    }

}
