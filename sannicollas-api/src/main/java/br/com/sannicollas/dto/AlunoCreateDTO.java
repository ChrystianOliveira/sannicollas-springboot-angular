package br.com.sannicollas.dto;

import br.com.sannicollas.model.Documentacao;
import br.com.sannicollas.model.Situacao;
import br.com.sannicollas.model.Turma;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlunoCreateDTO {

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 5, max = 100, message = "O tamanho deve ser maior que 5 caracteres")
    private String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDeNascimento;

    @NotNull(message = "Preenchimento obrigatório")
    private Turma turma;

    private String cidade;

    private String estado;

    private String nomeDoPai;

    private String nomeDaMae;

    @NotNull(message = "Preenchimento da situação do aluno obrigatório")
    private Situacao situacao;

    @NotNull(message = "Preenchimento do status da documentação do aluno obrigatório")
    private Documentacao documentacao;

}
