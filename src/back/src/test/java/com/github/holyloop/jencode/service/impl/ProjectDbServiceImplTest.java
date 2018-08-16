package com.github.holyloop.jencode.service.impl;

import static org.junit.Assert.*;

import com.github.holyloop.jencode.MainApplication;
import com.github.holyloop.jencode.dao.ProjectDbMapper;
import com.github.holyloop.jencode.dao.ProjectMapper;
import com.github.holyloop.jencode.dto.ProjectDbInsertDto;
import com.github.holyloop.jencode.dto.ProjectDbUpdateDto;
import com.github.holyloop.jencode.except.BusinessException;
import com.github.holyloop.jencode.model.Project;
import com.github.holyloop.jencode.model.ProjectDb;
import com.github.holyloop.jencode.model.ProjectDbExample;
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
@SpringBootTest(classes = {MainApplication.class})
@ActiveProfiles("test")
public class ProjectDbServiceImplTest {

    @Autowired
    private ProjectDbServiceImpl projectDbService;
    @Autowired
    private ProjectDbMapper projectDbMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Test
    public void test() {
        assertNotNull(projectDbService);
        assertNotNull(projectDbMapper);
    }

    @Test
    @Transactional
    @Rollback
    public void testAddProjectDbBasic() {
        ProjectDbExample selectAll = new ProjectDbExample();
        long beforeInsertCount = projectDbMapper.countByExample(selectAll);

        Project project = new Project();
        project.setTitle("title");
        projectMapper.insertSelective(project);

        ProjectDbInsertDto insertDto = new ProjectDbInsertDto();
        insertDto.setHost("host");
        insertDto.setPort(1);
        insertDto.setUser("user");
        insertDto.setPassword("pass");
        insertDto.setDbname("db");
        insertDto.setProjectId(project.getId());

        projectDbService.addProjectDb(insertDto);

        long afterInsertCount = projectDbMapper.countByExample(selectAll);
        assertEquals(beforeInsertCount + 1, afterInsertCount);
    }

    @Test
    @Transactional
    @Rollback
    public void testAddProjectDbExcept() {
        // dto is null
        try {
            projectDbService.addProjectDb(null);
            fail("should have thrown BusinessException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        // projectId is null
        try {
            ProjectDbInsertDto dto = new ProjectDbInsertDto();
            projectDbService.addProjectDb(dto);
            fail("should have thrown BusinessException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        // projectDb existed
        ProjectDb entity = new ProjectDb();
        Long projectId = 1L;
        entity.setProjectId(projectId);
        projectDbMapper.insertSelective(entity);
        try {
            ProjectDbInsertDto dto = new ProjectDbInsertDto();
            dto.setProjectId(projectId);
            projectDbService.addProjectDb(dto);
            fail("should have thrown BusinessException");
        } catch (BusinessException e) {
            assertTrue(true);
        }

        // project does't exist
        try {
            ProjectDbInsertDto dto = new ProjectDbInsertDto();
            dto.setProjectId(projectId + 1);
            projectDbService.addProjectDb(dto);
            fail("should have thrown BusinessException");
        } catch (BusinessException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testUpdateProjectDbExcept() {
        // dto is null
        try {
            projectDbService.updateProjectDb(null);
            fail("should have thrown BusinessException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
        // projectId is null
        try {
            ProjectDbUpdateDto dto = new ProjectDbUpdateDto();
            projectDbService.updateProjectDb(dto);
            fail("should have thrown BusinessException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteProjectDbs() {
        List<Long> toDelIds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProjectDb projectDb = new ProjectDb();
            projectDb.setHost("host" + i);
            projectDb.setProjectId(1L);
            projectDbMapper.insertSelective(projectDb);
            if (i % 2 == 0) {
                toDelIds.add(projectDb.getId());
            }
        }

        projectDbService.deleteProjectDbs(toDelIds);

        ProjectDbExample deletedExample = new ProjectDbExample();
        deletedExample.createCriteria().andDeletedEqualTo((byte) 1);
        List<ProjectDb> deletedProjectDbs = projectDbMapper.selectByExample(deletedExample);

        assertEquals(toDelIds.size(), deletedProjectDbs.size());
        for (ProjectDb projectDb : deletedProjectDbs) {
            assertTrue(toDelIds.contains(projectDb.getId()));
        }
    }
}
