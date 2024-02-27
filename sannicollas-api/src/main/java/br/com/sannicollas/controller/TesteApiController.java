package br.com.sannicollas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("check-api")
@Tag(name = "API REST Teste", description = "Controlador para testar a API")
public class TesteApiController {

    @Operation(summary = "Verifica se a API está funcionando", description = "Este endpoint verifica se a API está funcionando corretamente.")
    @GetMapping
    public String checkApi() {
        return "api está funcionando";
    }

}
