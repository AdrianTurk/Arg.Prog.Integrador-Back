package com.portfolio.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.backend.dao.IProjectDAO;
import com.portfolio.backend.model.Project;

@Service
@Transactional
public class ProjectService {
    @Autowired
    IProjectDAO projectDAO;

    public List<Project> list() {
        return projectDAO.findAll();
    }

    public Optional<Project> getByName(String name) {
        return projectDAO.findByName(name);
    }

    public boolean existsByName(String name) {
        return projectDAO.existsByName(name);
    }

    public Optional<Project> getById(Long id) {
        return projectDAO.findById(id);
    }

    public boolean existsByID(int id) {
        return projectDAO.existsById((long) (id));
    }

    public void save(Project skill) {
        projectDAO.save(skill);
    }

    public void delete(long id) {
        projectDAO.deleteById(id);
    }
}
