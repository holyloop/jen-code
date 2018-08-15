package com.github.holyloop.jencode.service.impl;

import com.github.holyloop.jencode.dao.ProjectDbMapper;
import com.github.holyloop.jencode.dto.ProjectDbInsertDto;
import com.github.holyloop.jencode.dto.ProjectDbUpdateDto;
import com.github.holyloop.jencode.service.ProjectDbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectDbServiceImpl implements ProjectDbService {

    @Autowired
    private ProjectDbMapper projectDbMapper;

    @Override
    public void addProjectDb(ProjectDbInsertDto dto) {
        // TODO
    }

    @Override
    public void updateProjectDb(ProjectDbUpdateDto dto) {
        // TODO
    }

    @Override
    public void deleteProjectDbs(List<Long> ids) {
        // TODO
    }

}
