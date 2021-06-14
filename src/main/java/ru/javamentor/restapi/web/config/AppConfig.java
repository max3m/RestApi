package ru.javamentor.restapi.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("loginpage");
        registry.addViewController("/admin").setViewName("adminpage");
        registry.addViewController("/user").setViewName("userpage");
    }
}
