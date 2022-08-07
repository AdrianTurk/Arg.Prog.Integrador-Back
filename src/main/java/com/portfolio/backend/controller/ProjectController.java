package com.portfolio.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.backend.model.Project;
import com.portfolio.backend.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/project")
@CrossOrigin
@Slf4j
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping("/list")
    public ResponseEntity<List<Project>> list(HttpServletRequest request) {
        log.info("Project list requested FROM : {}", request.getRemoteAddr());
        return ResponseEntity.ok().body(projectService.list());
    }
}
