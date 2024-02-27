package br.com.sannicollas.mapper;

import br.com.sannicollas.dto.TurmaDTO;
import br.com.sannicollas.model.Turma;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TurmaMapper {

    TurmaMapper INST = Mappers.getMapper(TurmaMapper.class);

    TurmaDTO modelToDto(Turma model);

}
