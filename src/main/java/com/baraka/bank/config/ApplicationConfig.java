package com.baraka.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This configuration is for Swagger where we are giving title,Description,version of Application.
 * Here We are Using Swagger Version 2
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 */

@Configuration
@EnableSwagger2
public class ApplicationConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("BARAKA BANKING APPLICATION REST API")
                .description("API for BARAKA Banking Application.")
                // Version is SNAPSHOT because its still in development not RELEASED in production yet
                .version("0.0.1-SNAPSHOT").build();
    }
}
