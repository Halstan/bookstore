package com.rikazzo.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.rikazzo.back.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiDetail());
    }

    private ApiInfo apiDetail(){
        return new ApiInfo(
                "Rikazzo Librería API documentation",
                "Documentación para la API de Rikazzo Librería",
                "1.0",
                "Project - Free to use",
                new Contact("Rikazzo Librería", "https://github.com/Halstan/library", "enzoarauco@gmail.com"),
                "MIT License",
                "",
                Collections.emptyList());
    }

}
