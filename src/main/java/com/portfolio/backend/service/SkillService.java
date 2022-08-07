package com.portfolio.backend.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.backend.dao.ISkillDAO;
import com.portfolio.backend.model.Skill;

@Service
@Transactional
public class SkillService {
    @Autowired
    ISkillDAO skillDAO;

    public List<Skill> list() {
        return skillDAO.findAll();
    }

    public Optional<Skill> getByName(String name){
        return skillDAO.findByName(name);
    }
    
    public Optional<Skill> getById(Long id){
        return skillDAO.findById(id);
    }

    public void save(Skill skill){
        skillDAO.save(skill);
    }

    public void  delete(long id){
        skillDAO.deleteById(id);
    }
}
