package com.hubfintech.app.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
public class CorsConfiguration {
	
	//This can be used in combination with @CrossOrigin on the controller & method.

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD","OPTIONS")
                        .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept");
            }
        };
    }

}
