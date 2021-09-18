package ai.tech.service.mapper;

import ai.tech.domain.model.Computer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComputerMapper {
  ComputerMapper INSTANCE = Mappers.getMapper(ComputerMapper.class);

  Computer toEntity(ai.tech.openApi.domain.model.Computer computerDto);
  ai.tech.openApi.domain.model.Computer toDto(Computer computer);
  Computer mergeComputer(ai.tech.openApi.domain.model.Computer computerDto, @MappingTarget Computer computer);
}