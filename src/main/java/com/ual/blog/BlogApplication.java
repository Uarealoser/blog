package com.ual.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BlogApplication extends SpringBootServletInitializer {
//    打成war包，需要继承SpringBootServletInitializer，并重写configure方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
            return builder.sources(SpringBootApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
