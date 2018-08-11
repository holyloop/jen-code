package com.github.holyloop.jencode.converter;

import com.github.holyloop.jencode.dto.ProjectInsertDto;
import com.github.holyloop.jencode.dto.ProjectUpdateDto;
import com.github.holyloop.jencode.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectConverter {

    ProjectConverter converter = Mappers.getMapper( ProjectConverter.class );

    Project insertDtoToEntity(ProjectInsertDto dto);

    Project updateDtoToEntity(ProjectUpdateDto dto);

}
