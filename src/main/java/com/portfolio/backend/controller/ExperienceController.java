package com.portfolio.backend.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.backend.DTO.ExperienceDTO;
import com.portfolio.backend.DTO.Message;
import com.portfolio.backend.model.Experience;

import com.portfolio.backend.service.ExperienceService;

@RestController
@RequestMapping("/experience")
@CrossOrigin
public class ExperienceController {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ExperienceService experienceService;

    @RequestMapping("/list")
    public ResponseEntity<List<Experience>> list() {

        return new ResponseEntity<>(experienceService.list(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienceDTO experienceDTO) {
        if (StringUtils.isBlank(experienceDTO.getCompanyName())) {
            return new ResponseEntity(new Message("MSG_ERR_COMPANY_NAME_BLANK"), HttpStatus.BAD_REQUEST);
        }
        Experience entity = new Experience(experienceDTO.getCompanyName(),
                experienceDTO.getDetails(),
                experienceDTO.getPeriod(),
                experienceDTO.getRecomendations());
        experienceService.save(entity);
        return new ResponseEntity(new Message("MSG_ADDED"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody ExperienceDTO experienceDTO) {
        if (!experienceService.existsById(id)) {
            return new ResponseEntity(new Message("MSG_ERR_EXP_ID_NOT_FOUND"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(experienceDTO.getCompanyName())) {
            return new ResponseEntity(new Message("MSG_ERR_COMPANY_NAME_INVALID"), HttpStatus.BAD_REQUEST);
        }

        Experience entry = experienceService.getById(id).get();

        //TODO: Is this OK? ::
        Experience oldData = new Experience();
        modelMapper.map(entry, oldData);
        modelMapper.map(experienceDTO, entry);

        modelMapper.map(experienceDTO, entry);
        entry.setId(oldData.getId());
        experienceService.save(entry);
        return new ResponseEntity(new Message("MSG_ADDED"), HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if (!experienceService.existsById(id)) {
            return new ResponseEntity(new Message("MSG_ERR_EXP_ID_NOT_FOUND"), HttpStatus.BAD_REQUEST);
        }
        experienceService.delete(id);
        return new ResponseEntity(new Message("MSG_REMOVED"), HttpStatus.OK);
    }
}
