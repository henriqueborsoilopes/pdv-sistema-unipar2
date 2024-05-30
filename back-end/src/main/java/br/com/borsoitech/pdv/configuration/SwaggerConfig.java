package br.com.borsoitech.pdv.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import br.com.borsoitech.pdv.entity.Produto;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
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
                                .url("hhttps://github.com/henriqueborsoilopes")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor local")
                )).components(new Components()
                        .addSchemas("PageProduto", new Schema<Page<Produto>>()
                                .type("object")
                                .addProperty("content", new ArraySchema().items(new Schema<Produto>().$ref("/produtos")))
                                .addProperty("pageNumber", new Schema<Integer>().type("integer"))
                                .addProperty("pageSize", new Schema<Integer>().type("integer"))
                                .addProperty("totalElements", new Schema<Long>().type("integer"))
                                .addProperty("totalPages", new Schema<Integer>().type("integer"))
                        )
                );
    }
}
