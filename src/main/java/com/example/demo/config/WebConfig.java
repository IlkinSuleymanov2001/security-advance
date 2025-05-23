package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${api.base-path}")
    private String basePath;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // Bütün @RestController-lara base path əlavə et
        configurer.addPathPrefix(basePath,
                clazz -> clazz.isAnnotationPresent(RestController.class));
    }
}
