package com.portfolio.backend.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.backend.security.DAO.RoleRepository;
import com.portfolio.backend.security.enums.RoleFlag;
import com.portfolio.backend.security.model.LoginRole;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<LoginRole> getByRoleFlag(RoleFlag roleFlag){
        return roleRepository.findByRoleFlag(roleFlag);
    }

    public void save(LoginRole role){
        roleRepository.save(role);
    }
}
