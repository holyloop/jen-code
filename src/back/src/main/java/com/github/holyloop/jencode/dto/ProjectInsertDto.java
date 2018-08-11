package com.github.holyloop.jencode.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectInsertDto extends BaseDto {

    private String title;

    private String author;

    private String description;

    private String projectPath;

}
