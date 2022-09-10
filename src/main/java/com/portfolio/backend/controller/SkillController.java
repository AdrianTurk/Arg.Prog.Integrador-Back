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
import com.portfolio.backend.DTO.SkillDTO;
import com.portfolio.backend.model.Skill;
import com.portfolio.backend.service.SkillService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/skill")
@CrossOrigin
@Slf4j
public class SkillController {
    // TODO: Data must be from one user: /list/{id} pathvariable

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    SkillService skillService;

    @RequestMapping("/list")
    public ResponseEntity<List<Skill>> list(HttpServletRequest request) {
        log.info("Skill list requested FROM : {}", request.getRemoteAddr());
        return ResponseEntity.ok().body(skillService.list());
    }

    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestBody SkillDTO skill) {
        if (skillService.existsByName(skill.getName()))
            return new ResponseEntity<Message>(new Message("El skill ya existe"), HttpStatus.BAD_REQUEST);
        Skill fullSkill = new Skill();

        modelMapper.map(skill, fullSkill);
        skillService.save(fullSkill);

        return new ResponseEntity<Message>(new Message("Skill agregado"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable Long id, @RequestBody Skill skill) {
        if (!skillService.existsByID(id))
            return new ResponseEntity<Message>(new Message("El skill no existe"), HttpStatus.BAD_REQUEST);

        skillService.save(skill);
        return new ResponseEntity<Message>(new Message("Skill modificado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!skillService.existsByID(id))
            return new ResponseEntity<Message>(new Message("El skill no existe"), HttpStatus.BAD_REQUEST);
        skillService.delete(id);
        return new ResponseEntity<Message>(new Message("Skill eliminado"), HttpStatus.OK);
    }
}
