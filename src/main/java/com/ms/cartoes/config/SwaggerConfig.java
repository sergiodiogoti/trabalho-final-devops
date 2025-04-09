package com.ms.cartoes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Cartoes-ms")
                        .description("Microservice responsável por gerenciar propostas de crédito, emitir cartões e gerenciar clientes.\n")
                        .version("versão 0.0.1"));
    }
}
