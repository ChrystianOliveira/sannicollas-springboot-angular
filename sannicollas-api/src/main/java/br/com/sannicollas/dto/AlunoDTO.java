package br.com.sannicollas.dto;

import br.com.sannicollas.model.Documentacao;
import br.com.sannicollas.model.Situacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlunoDTO {

    private UUID id;

    private String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeNascimento;

    private TurmaDTO turma;

    private String cidade;

    private String estado;

    private String nomeDoPai;

    private String nomeDaMae;

    private Situacao situacao;

    private Documentacao documentacao;

}
