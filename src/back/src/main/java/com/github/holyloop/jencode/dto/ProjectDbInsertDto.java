package com.github.holyloop.jencode.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectDbInsertDto extends BaseDto {

    private String host;

    private Integer port;

    private String user;

    private String password;

    private String dbname;

    private Long projectId;

}
