package com.portfolio.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.backend.dao.IEducationDAO;
import com.portfolio.backend.model.Education;

@Service
@Transactional
public class EducationService {
    @Autowired
    IEducationDAO educationDAO;

    public List<Education> list() {
        return educationDAO.findAll();
    }

    public Optional<Education> getById(long id) {
        return educationDAO.findById(id);
    }

    public Optional<Education> getByEntityName(String entityName) {
        return educationDAO.findByEntityName(entityName);
    }

    public void save(Education entity) {
        educationDAO.save(entity);
    }

    public void delete(long id) {
        educationDAO.deleteById(id);
    }

    public boolean existsById(long id) {
        return educationDAO.existsById(id);
    }

    public boolean existsByEntityName(String entityName) {
        return educationDAO.existsByEntityName(entityName);
    }

}
