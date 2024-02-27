package br.com.sannicollas.mapper;

import br.com.sannicollas.dto.AlunoCreateDTO;
import br.com.sannicollas.dto.AlunoDTO;
import br.com.sannicollas.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoMapper INST = Mappers.getMapper(AlunoMapper.class);

    AlunoDTO modelToDTO(Aluno aluno);

    @Mapping(target = "id", ignore = true)
    Aluno dtoToModel(AlunoCreateDTO dto);

}
