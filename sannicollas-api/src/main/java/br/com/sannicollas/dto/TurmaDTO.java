package br.com.sannicollas.dto;

import br.com.sannicollas.model.Nivel;
import br.com.sannicollas.model.Turno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurmaDTO {

    private Long id;

    private String descricao;

    private Turno turno;

    private Nivel nivel;

    private Long numeroDeAlunos;

}
