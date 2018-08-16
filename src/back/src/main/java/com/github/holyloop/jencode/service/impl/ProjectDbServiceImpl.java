package com.github.holyloop.jencode.service.impl;

import com.github.holyloop.jencode.converter.ProjectDbConverter;
import com.github.holyloop.jencode.dao.ProjectDbMapper;
import com.github.holyloop.jencode.dao.ProjectMapper;
import com.github.holyloop.jencode.dto.ProjectDbInsertDto;
import com.github.holyloop.jencode.dto.ProjectDbUpdateDto;
import com.github.holyloop.jencode.except.BusinessException;
import com.github.holyloop.jencode.model.Project;
import com.github.holyloop.jencode.model.ProjectDb;
import com.github.holyloop.jencode.model.ProjectDbExample;
import com.github.holyloop.jencode.model.ProjectExample;
import com.github.holyloop.jencode.service.ProjectDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ProjectDbServiceImpl implements ProjectDbService {

    @Autowired
    private ProjectDbMapper projectDbMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public void addProjectDb(ProjectDbInsertDto dto) {
        Assert.notNull(dto, "insert dto can't be null");
        Assert.notNull(dto.getProjectId(), "project id can't be null");
        ProjectDb entity = ProjectDbConverter.converter.insertDtoToEntity(dto);

        // projectDb existence check
        ProjectDbExample duplicateExample = new ProjectDbExample();
        duplicateExample.createCriteria().andProjectIdEqualTo(dto.getProjectId()).andDeletedEqualTo((byte) 0);
        List<ProjectDb> duplicateProjectDb = projectDbMapper.selectByExample(duplicateExample);
        if (duplicateProjectDb != null && duplicateProjectDb.size() > 0) {
            throw new BusinessException("project db existed");
        }
        // project existence check
        ProjectExample projectExample = new ProjectExample();
        projectExample.createCriteria().andIdEqualTo(dto.getProjectId()).andDeletedEqualTo((byte) 0);
        List<Project> projects = projectMapper.selectByExample(projectExample);
        if (projects == null || projects.size() == 0) {
            throw new BusinessException("project does't exist");
        }

        entity.setAddTime(new Date());
        log.info("addProjectDb, entity: {}", entity);
        projectDbMapper.insertSelective(entity);
    }

    @Override
    public void updateProjectDb(ProjectDbUpdateDto dto) {
        Assert.notNull(dto, "update dto can't be null");
        Assert.notNull(dto.getId(), "project db id can't be null");

        ProjectDb entity = ProjectDbConverter.converter.updateDtoToEntity(dto);
        log.info("updateProjectDb, entity: {}", entity);
        projectDbMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public void deleteProjectDbs(List<Long> ids) {
        if (ids == null || ids.size() == 0) {
            return;
        }
        log.info("deleteProjectDbs, ids: {}", ids);
        for (Long id : ids) {
            ProjectDb entity = new ProjectDb();
            entity.setId(id);
            entity.setDeleted((byte) 1);
            projectDbMapper.updateByPrimaryKeySelective(entity);
        }
    }

}
