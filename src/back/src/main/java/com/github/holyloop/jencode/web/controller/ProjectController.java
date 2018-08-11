package com.github.holyloop.jencode.web.controller;

import com.github.holyloop.jencode.dto.ProjectInsertDto;
import com.github.holyloop.jencode.dto.ProjectUpdateDto;
import com.github.holyloop.jencode.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/project")
public class ProjectController implements BaseController {

    @Autowired
    private ProjectService projectService;

    @PostMapping()
    public void addProject(@RequestBody ProjectInsertDto dto) {
        projectService.addProject(dto);
    }

    @PutMapping()
    public void updateProject(@RequestBody ProjectUpdateDto dto) {
        projectService.updateProject(dto);
    }

    @DeleteMapping()
    public void deleteProjects(@RequestParam List<Long> ids) {
        projectService.deleteProjects(ids);
    }

}
