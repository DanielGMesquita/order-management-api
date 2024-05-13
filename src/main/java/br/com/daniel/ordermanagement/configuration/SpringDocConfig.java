package br.com.daniel.ordermanagement.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class SpringDocConfig {
  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Order Management API")
                .description(
                    "API utilizada para criação e gestão de produtos, pedidos e cateogrias de produtos.")
                .version("1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
        .externalDocs(
            new ExternalDocumentation()
                .description("Link do Repositório da Aplicação - APIRestful Documentation")
                .url("https://github.com/DanielGMesquita/order-management-api"));
  }
}
