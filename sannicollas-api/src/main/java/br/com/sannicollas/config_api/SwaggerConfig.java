package br.com.sannicollas.config_api;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SanNicollas API")
                        .description("Documentação de todos os serviços disponíveis para comunicação" +
                                " com a API do Sistema de cadastro e geração de relatórios de alunos.")
                        .version("1.0")
                        .termsOfService("Termo de uso: Open Source")
                ).externalDocs(
                        new ExternalDocumentation()
                                .description("Chrystian Max de Oliveira Cavalcante")
                                .url("https://github.com/ChrystianOliveira/sannicollas.git"));
    }
}
