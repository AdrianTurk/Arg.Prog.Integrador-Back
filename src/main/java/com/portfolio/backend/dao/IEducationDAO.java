package com.portfolio.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.backend.model.Education;

@Repository
public interface IEducationDAO extends JpaRepository<Education, Long> {

    public Optional<Education> findByEntityName(String entityName);

    public boolean existsByEntityName(String entityName);
}