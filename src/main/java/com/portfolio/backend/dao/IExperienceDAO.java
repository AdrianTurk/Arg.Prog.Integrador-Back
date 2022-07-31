package com.portfolio.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.backend.model.Experience;

@Repository
public interface IExperienceDAO extends JpaRepository<Experience, Long>{
    public Optional<Experience> findByCompanyName(String companyName);
    public boolean existsByCompanyName(String companyName);
}
