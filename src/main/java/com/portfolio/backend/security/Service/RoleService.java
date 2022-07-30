package com.portfolio.backend.security.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.backend.security.DAO.IRoleDAO;
import com.portfolio.backend.security.entity.Role;
import com.portfolio.backend.security.enums.RoleEnum;

@Service
@Transactional
public class RoleService {
    @Autowired
    IRoleDAO roleDAO;

    public Optional<Role> getByRole(RoleEnum role){
        return roleDAO.findByRole(role);
    }

    public void save(Role role){
        roleDAO.save(role);
    }
}
