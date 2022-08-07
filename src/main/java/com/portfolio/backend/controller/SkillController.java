package com.portfolio.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.backend.model.Skill;
import com.portfolio.backend.service.SkillService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/skill")
@CrossOrigin
@Slf4j
public class SkillController {

    @Autowired
    SkillService skillService;

    @RequestMapping("/list")
    public ResponseEntity<List<Skill>> list(HttpServletRequest request) {
        log.info("Skill list requested FROM : {}", request.getRemoteAddr());
        return ResponseEntity.ok().body(skillService.list());
    }
}
