package com.github.holyloop.jencode.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectDb extends BaseEntity {
    private Long id;

    private String host;

    private Integer port;

    private String user;

    private String password;

    private String dbname;

    private Long projectId;

    private Byte deleted;

    private Date addTime;

    private Date modifyTime;
}