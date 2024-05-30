package br.com.borsoitech.pdv.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PDV API")
                        .version("1.0")
                        .description("Recursos para acessar ter acesso aos dados")
                        .contact(new Contact()
                                .name("Henrique Borsoi Lopes")
                                .email("henriqueborsoilopes@gmail.com")
                                .url("https://github.com/henriqueborsoilopes")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor local")
                ))
                .components(new Components().addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"));
    }
}
