package com.portfolio.backend.security.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.backend.security.enums.RoleFlag;
import com.portfolio.backend.security.model.LoginRole;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<LoginRole, Integer> {
    Optional<LoginRole> findByRoleFlag(RoleFlag roleFlag);
}
