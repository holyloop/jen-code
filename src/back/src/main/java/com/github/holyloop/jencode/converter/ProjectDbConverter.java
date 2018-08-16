package com.github.holyloop.jencode.converter;

import com.github.holyloop.jencode.dto.ProjectDbInsertDto;
import com.github.holyloop.jencode.dto.ProjectDbUpdateDto;
import com.github.holyloop.jencode.model.ProjectDb;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectDbConverter {

    ProjectDbConverter converter = Mappers.getMapper( ProjectDbConverter.class );

    ProjectDb insertDtoToEntity(ProjectDbInsertDto dto);

    ProjectDb updateDtoToEntity(ProjectDbUpdateDto dto);

}
