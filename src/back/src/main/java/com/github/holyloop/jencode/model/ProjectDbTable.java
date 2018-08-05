package com.github.holyloop.jencode.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectDbTable extends BaseEntity {
    private Long id;

    private Long projectDbId;

    private String tableName;

    private Byte deleted;

    private Date addTime;

    private Date modifyTime;
}