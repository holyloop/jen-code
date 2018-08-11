package com.github.holyloop.jencode.service;

import com.github.holyloop.jencode.dto.ProjectInsertDto;
import com.github.holyloop.jencode.dto.ProjectUpdateDto;
import com.github.holyloop.jencode.model.Project;

import java.util.List;

public interface ProjectService extends BaseService<Project> {

    /**
     * add project
     * @param dto
     */
    void addProject(ProjectInsertDto dto);

    /**
     * update project
     * @param dto
     */
    void updateProject(ProjectUpdateDto dto);

    /**
     * delete projects by ids
     * @param ids
     */
    void deleteProjects(List<Long> ids);

}
