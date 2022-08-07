package com.portfolio.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.backend.model.Skill;

@Repository
public interface ISkillDAO extends JpaRepository<Skill, Long> {
    public boolean existsByName(String name);

    public Optional<Skill> findByName(String Name);
}
