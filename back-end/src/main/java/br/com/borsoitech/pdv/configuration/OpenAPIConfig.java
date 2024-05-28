package br.com.borsoitech.pdv.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                                .title("REST API - PDV")
                                .description("API")
                                .version("v1")
                                .contact(new Contact().name("PDV").url("https://pdv.borsoitech.com.br/")));
    }
}