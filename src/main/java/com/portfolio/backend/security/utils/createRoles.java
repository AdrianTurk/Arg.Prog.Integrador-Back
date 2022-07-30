package com.portfolio.backend.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.portfolio.backend.security.enums.RoleFlag;
import com.portfolio.backend.security.model.LoginRole;
import com.portfolio.backend.security.service.RoleService;


// PARA GENERAL LOS ROLES
//TODO:Que regenere automaticamente

// @Component
// public class createRoles implements CommandLineRunner{
    
//     @Autowired
//     RoleService rolService;

//     @Override
//     public void run(String... args) throws Exception {
//         LoginRole rolAdmin = new LoginRole(RoleFlag.ROLE_ADMIN);
//         LoginRole rolUser = new LoginRole(RoleFlag.ROLE_USER);

//         rolService.save(rolAdmin);
//         rolService.save(rolUser);
//     }
// }
