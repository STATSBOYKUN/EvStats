
package com.umaru.evstats.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/boostrap/**",
                        "/css/**",
                        "/fonts/**",
                        "/img/**",
                        "/js/**",
                        "/invoices/**",
                        "/poster/**",
                        "/static/**")
                .addResourceLocations(
                        "classpath:/static/boostrap/",
                        "classpath:/static/css/",
                        "classpath:/static/fonts/",
                        "classpath:/static/img/",
                        "classpath:/static/js/",
                        "classpath:/static/invoices/",
                        "classpath:/static/poster/",
                        "classpath:/static/"
                );
    }
    
}
