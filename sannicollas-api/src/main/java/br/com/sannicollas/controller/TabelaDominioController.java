package br.com.sannicollas.controller;

import br.com.sannicollas.service.TabelaDominioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tabelas-dominio")
@RequiredArgsConstructor
@Tag(name = "API REST Tabela Dominio")
public class TabelaDominioController {

    private final TabelaDominioService tabelaDominioService;

    @Operation(summary = "Busca uma lista com a relação de todas as tabelas de dominio", description = "Retorna os IDs das tabelas de dominio do banco de dados")
    @GetMapping
    public JSONObject findAllTabelasAux() {
        return tabelaDominioService.buscarTabelasDeDominio();
    }

}
