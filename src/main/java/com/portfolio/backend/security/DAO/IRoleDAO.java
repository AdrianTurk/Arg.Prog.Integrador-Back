package com.portfolio.backend.security.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.backend.security.entity.Role;
import com.portfolio.backend.security.enums.RoleEnum;

@Repository
public interface IRoleDAO extends JpaRepository<Role, Integer>{
    Optional<Role> findByRole(RoleEnum role);
}
