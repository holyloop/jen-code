package com.github.holyloop.jencode.web.controller;

import com.github.holyloop.jencode.dto.ProjectDbInsertDto;
import com.github.holyloop.jencode.dto.ProjectDbUpdateDto;
import com.github.holyloop.jencode.service.ProjectDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/project/db")
public class ProjectDbController {

    @Autowired
    private ProjectDbService projectDbService;

    @PostMapping()
    public void addProjectDb(@RequestBody ProjectDbInsertDto dto) {
        projectDbService.addProjectDb(dto);
    }

    @PutMapping()
    public void updateProjectDb(@RequestBody ProjectDbUpdateDto dto) {
        projectDbService.updateProjectDb(dto);
    }

    @DeleteMapping()
    public void deleteProjectDbs(@RequestParam List<Long> ids) {
        projectDbService.deleteProjectDbs(ids);
    }

}
