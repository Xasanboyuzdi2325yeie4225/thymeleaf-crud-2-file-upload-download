package org.example.thymeleafmvccrudandfileuploadandfiledownload.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration {


    @PostConstruct
    public void init() {
        System.out.println("DASTUR ISHLADI");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("DASTUR QULADI");
    }


}
