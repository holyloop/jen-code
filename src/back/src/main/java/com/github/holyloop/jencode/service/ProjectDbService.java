package com.github.holyloop.jencode.service;

import com.github.holyloop.jencode.dto.ProjectDbInsertDto;
import com.github.holyloop.jencode.dto.ProjectDbUpdateDto;
import com.github.holyloop.jencode.model.ProjectDb;

import java.util.List;

public interface ProjectDbService extends BaseService<ProjectDb> {

    /**
     * add project db
     * @param dto
     */
    void addProjectDb(ProjectDbInsertDto dto);

    /**
     * update project db
     * @param dto
     */
    void updateProjectDb(ProjectDbUpdateDto dto);

    /**
     * delete projects by ids
     * @param ids
     */
    void deleteProjectDbs(List<Long> ids);

}
