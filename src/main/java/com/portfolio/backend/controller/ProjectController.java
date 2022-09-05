package com.portfolio.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.backend.DTO.Message;
import com.portfolio.backend.DTO.ProjectDTO;
import com.portfolio.backend.model.Project;
import com.portfolio.backend.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/project")
@CrossOrigin
@Slf4j
public class ProjectController {
    // TODO: Data must be from one user: /list/{id} pathvariable

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ProjectService projectService;

    @RequestMapping("/list")
    public ResponseEntity<List<Project>> list(HttpServletRequest request) {
        log.info("Project list requested FROM : {}", request.getRemoteAddr());
        return ResponseEntity.ok().body(projectService.list());
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody Project project) {
        if (!projectService.existsByID(project.getId()))
            return new ResponseEntity<Message>(new Message("El proyecto no existe"), HttpStatus.BAD_REQUEST);

        projectService.save(project);
        return new ResponseEntity<Message>(new Message("Proyecto modificado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestBody ProjectDTO project) {

        if (projectService.existsByName(project.getName()))
            return new ResponseEntity<Message>(new Message("El proyecto ya existe"), HttpStatus.BAD_REQUEST);
        Project fullSkill = new Project();

        modelMapper.map(project, fullSkill);
        projectService.save(fullSkill);

        return new ResponseEntity<Message>(new Message("Proyecto agregado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        if (!projectService.existsByID(id))
            return new ResponseEntity<Message>(new Message("El proyecto no existe"), HttpStatus.BAD_REQUEST);
        projectService.delete(id);
        return new ResponseEntity<Message>(new Message("Proyecto eliminado"), HttpStatus.OK);
    }
}
