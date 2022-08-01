package com.portfolio.backend.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.backend.DTO.EducationDTO;
import com.portfolio.backend.DTO.Message;
import com.portfolio.backend.model.Education;
import com.portfolio.backend.service.EducationService;

@RestController
@RequestMapping("/education")
@CrossOrigin
public class EducationController {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    EducationService educationService;

    @GetMapping("/list")    //TODO:PostMAPPING?
    public ResponseEntity<List<Education>> list() {

        return new ResponseEntity<>(educationService.list(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EducationDTO educationDTO) {
        if (StringUtils.isBlank(educationDTO.getEntityName())) {
            return new ResponseEntity(new Message("MSG_ERR_ENTITY_NAME_BLANK"), HttpStatus.BAD_REQUEST);
        }
        Education entity = new Education(educationDTO.getEntityName(),
                educationDTO.getTitle(),
                educationDTO.getDetails());

        educationService.save(entity);
        return new ResponseEntity(new Message("MSG_ADDED"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody EducationDTO experienceDTO) {
        if (!educationService.existsById(id)) {
            return new ResponseEntity(new Message("MSG_ERR_EXP_ID_NOT_FOUND"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(experienceDTO.getEntityName())) {
            return new ResponseEntity(new Message("MSG_ERR_ENTITY_NAME_INVALID"), HttpStatus.BAD_REQUEST);
        }

        Education entry = educationService.getById(id).get();

        // TODO: Is this OK? ::
        Education oldData = new Education();
        modelMapper.map(entry, oldData);
        modelMapper.map(experienceDTO, entry);

        modelMapper.map(experienceDTO, entry);
        entry.setId(oldData.getId());
        educationService.save(entry);
        return new ResponseEntity(new Message("MSG_ADDED"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if (!educationService.existsById(id)) {
            return new ResponseEntity(new Message("MSG_ERR_EXP_ID_NOT_FOUND"), HttpStatus.BAD_REQUEST);
        }
        educationService.delete(id);
        return new ResponseEntity(new Message("MSG_REMOVED"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id") int id) {
        if (!educationService.existsById(id))
            return new ResponseEntity(new Message("MSG_NO_ENCOUNTRED_EXPERIENCE"), HttpStatus.NOT_FOUND);

        return new ResponseEntity(educationService.getById(id).get(), HttpStatus.OK);
    }
}
