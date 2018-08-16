package com.github.holyloop.jencode.service.impl;

import static org.junit.Assert.*;

import com.github.holyloop.jencode.MainApplication;
import com.github.holyloop.jencode.dao.ProjectMapper;
import com.github.holyloop.jencode.dto.ProjectInsertDto;
import com.github.holyloop.jencode.dto.ProjectUpdateDto;
import com.github.holyloop.jencode.except.BusinessException;
import com.github.holyloop.jencode.model.Project;
import com.github.holyloop.jencode.model.ProjectExample;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { MainApplication.class })
@ActiveProfiles("test")
public class ProjectServiceImplTest {

    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private ProjectMapper projectMapper;

    @Test
    public void test() {
        assertNotNull(projectService);
        assertNotNull(projectMapper);
    }

    @Test
    @Transactional
    @Rollback
    public void testAddProjectBasic() {
        try {
            projectService.addProject(null);
            fail("should have thrown BusinessException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        ProjectExample selectAll = new ProjectExample();
        int beforeInsertCount = projectMapper.selectByExample(selectAll).size();

        ProjectInsertDto insertDto = new ProjectInsertDto();
        insertDto.setAuthor("author");
        insertDto.setDescription("desc");
        insertDto.setTitle("title");
        projectService.addProject(insertDto);

        int afterInsertCount = projectMapper.selectByExample(selectAll).size();
        assertEquals(beforeInsertCount + 1, afterInsertCount);
    }

    @Test
    @Transactional
    @Rollback
    public void testAddProjectDuplicated() {
        String duplicatedTitle = "title";
        String nonDuplicatedTitle = "title1";

        Project insertEntity = new Project();
        insertEntity.setAuthor("author");
        insertEntity.setDescription("desc");
        insertEntity.setTitle(duplicatedTitle);
        projectMapper.insertSelective(insertEntity);

        ProjectInsertDto insertDto = new ProjectInsertDto();
        insertDto.setAuthor("author");
        insertDto.setDescription("desc");
        insertDto.setTitle(duplicatedTitle);
        try {
            projectService.addProject(insertDto);
            fail("should have thrown BusinessException");
        } catch (BusinessException e) {
            assertTrue(true);
        }

        insertDto.setTitle(nonDuplicatedTitle);
        projectService.addProject(insertDto);
    }

    @Test
    public void testUpdateProjectExcept() {
        try {
            projectService.updateProject(null);
            fail("should have thrown BusinessException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            projectService.updateProject(new ProjectUpdateDto());
            fail("should have thrown BusinessException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateProject() {
        Project entity = new Project();
        entity.setAuthor("author");
        entity.setDescription("desc");
        entity.setTitle("title");
        projectMapper.insertSelective(entity);

        String newTitle = "newTitle";
        ProjectUpdateDto updateDto = new ProjectUpdateDto();
        updateDto.setId(entity.getId());
        updateDto.setTitle(newTitle);
        projectService.updateProject(updateDto);

        Project entityInDb = projectMapper.selectByPrimaryKey(entity.getId());
        assertNotNull(entityInDb);
        assertEquals(entityInDb.getTitle(), newTitle);
        assertEquals(entityInDb.getAuthor(), entity.getAuthor());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteProjects() {
        List<Long> toDelIds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Project project = new Project();
            project.setTitle("title" + i);
            projectMapper.insertSelective(project);
            if (i % 2 == 0) {
                toDelIds.add(project.getId());
            }
        }

        projectService.deleteProjects(toDelIds);

        ProjectExample deletedExample = new ProjectExample();
        deletedExample.createCriteria().andDeletedEqualTo((byte) 1);
        List<Project> deletedProjects = projectMapper.selectByExample(deletedExample);

        assertEquals(toDelIds.size(), deletedProjects.size());
        for (Project project : deletedProjects) {
            assertTrue(toDelIds.contains(project.getId()));
        }
    }

}
