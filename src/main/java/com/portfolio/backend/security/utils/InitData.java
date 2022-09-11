package com.portfolio.backend.security.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.portfolio.backend.security.enums.RoleFlag;
import com.portfolio.backend.security.model.LoginRole;
import com.portfolio.backend.security.model.LoginUser;
import com.portfolio.backend.security.service.RoleService;
import com.portfolio.backend.security.service.UserAuthService;

// PARA GENERAL LOS ROLES
//TODO:Que regenere automaticamente

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Autowired
    UserAuthService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        if (roleService.list().isEmpty()){
            // Create Roles
            LoginRole rolAdmin = new LoginRole(RoleFlag.ROLE_ADMIN);
            LoginRole rolUser = new LoginRole(RoleFlag.ROLE_USER);
    
            roleService.save(rolAdmin);
            roleService.save(rolUser);
        }
        if (userService.list().isEmpty()){
            // create Root User
            LoginUser rootUser = new LoginUser("admin", "admin@server1.com", passwordEncoder.encode("TestPassword"));
            Set<LoginRole> roles = new HashSet<>();
            roles.add(roleService.getByRoleFlag(RoleFlag.ROLE_USER).get());
            roles.add(roleService.getByRoleFlag(RoleFlag.ROLE_ADMIN).get());
            rootUser.setRoles(roles);
            userService.save(rootUser);
        }
    }
}
