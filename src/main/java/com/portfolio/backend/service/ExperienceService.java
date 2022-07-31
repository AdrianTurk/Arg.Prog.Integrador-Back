package com.portfolio.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.backend.dao.IExperienceDAO;
import com.portfolio.backend.model.Experience;

@Service
@Transactional
public class ExperienceService {

    @Autowired
    IExperienceDAO experienceDAO;

    public List<Experience> list() {
        return experienceDAO.findAll();
    }

    public Optional<Experience> getById(long id) {
        return experienceDAO.findById(id);
    }

    public Optional<Experience> getByCompanyName(String companyName) {
        return experienceDAO.findByCompanyName(companyName);
    }

    public void save(Experience entity) {
        experienceDAO.save(entity);
    }

    public void delete(long id) {
        experienceDAO.deleteById(id);
    }

    public boolean existsById(long id) {
        return experienceDAO.existsById(id);
    }

    public boolean existsByCompany(String companyName) {
        return experienceDAO.existsByCompanyName(companyName);
    }

}
