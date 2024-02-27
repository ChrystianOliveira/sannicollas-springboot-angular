package br.com.sannicollas.service;

import br.com.sannicollas.model.Documentacao;
import br.com.sannicollas.model.Nivel;
import br.com.sannicollas.model.Situacao;
import br.com.sannicollas.model.Turno;
import br.com.sannicollas.repository.DocumentacaoRepository;
import br.com.sannicollas.repository.NivelRepository;
import br.com.sannicollas.repository.SituacaoRepository;
import br.com.sannicollas.repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TabelaDominioService {

    private final TurnoRepository turnoRepository;

    private final SituacaoRepository situacaoRepository;

    private final NivelRepository nivelRepository;

    private final DocumentacaoRepository documentacaoRepository;

    public JSONObject buscarTabelasDeDominio() {

        JSONObject tabelas = new JSONObject();

        List<Turno> turnoList = turnoRepository.findAll();
        tabelas.appendField("turnoList", turnoList);

        List<Situacao> situacaoList = situacaoRepository.findAll();
        tabelas.appendField("situacaoList", situacaoList);

        List<Nivel> nivelList = nivelRepository.findAll();
        tabelas.appendField("nivelList", nivelList);

        List<Documentacao> documentacaoList = documentacaoRepository.findAll();
        tabelas.appendField("documentacaoList", documentacaoList);

        return tabelas;
    }

}
