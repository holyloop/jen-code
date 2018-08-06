package com.github.holyloop.jencode.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity {
    private Long id;

    private String title;

    private String author;

    private String description;

    private String projectPath;

    private Byte deleted;

    private Date addTime;

    private Date modifyTime;
}