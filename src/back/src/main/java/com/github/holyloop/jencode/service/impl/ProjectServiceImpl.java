package com.github.holyloop.jencode.service.impl;

import com.github.holyloop.jencode.converter.ProjectConverter;
import com.github.holyloop.jencode.dao.ProjectMapper;
import com.github.holyloop.jencode.dto.ProjectInsertDto;
import com.github.holyloop.jencode.dto.ProjectUpdateDto;
import com.github.holyloop.jencode.except.BusinessException;
import com.github.holyloop.jencode.model.Project;
import com.github.holyloop.jencode.model.ProjectExample;
import com.github.holyloop.jencode.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public void addProject(ProjectInsertDto dto) {
        Assert.notNull(dto, "insert dto can't be null");
        Project entity = ProjectConverter.converter.insertDtoToEntity(dto);

        // project existence check
        ProjectExample duplicateExample = new ProjectExample();
        duplicateExample.createCriteria().andTitleEqualTo(dto.getTitle()).andDeletedEqualTo((byte) 0);
        List<Project> duplicateProjectList = projectMapper.selectByExample(duplicateExample);
        if (duplicateProjectList != null && duplicateProjectList.size() > 0) {
            throw new BusinessException("project existed");
        }

        entity.setAddTime(new Date());
        log.info("addProject, entity: {}", entity);
        projectMapper.insertSelective(entity);
    }

    @Override
    public void updateProject(ProjectUpdateDto dto) {
        Assert.notNull(dto, "update dto can't be null");
        Assert.notNull(dto.getId(), "project id can't be null");

        Project entity = ProjectConverter.converter.updateDtoToEntity(dto);
        log.info("updateProject, entity: {}", entity);
        projectMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public void deleteProjects(List<Long> ids) {
        if (ids == null || ids.size() == 0) {
            return;
        }
        log.info("deleteProjects, ids: {}", ids);
        for (Long id : ids) {
            Project entity = new Project();
            entity.setId(id);
            entity.setDeleted((byte) 1);
            projectMapper.updateByPrimaryKeySelective(entity);
        }
    }
}
