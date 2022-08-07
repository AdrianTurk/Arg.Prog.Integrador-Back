package com.portfolio.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.backend.model.Project;

@Repository
public interface IProjectDAO extends JpaRepository<Project, Long> {
    public Optional<Project> findByName(String name);

    public boolean existsByName(String name);
}
