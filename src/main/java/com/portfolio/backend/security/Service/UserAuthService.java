package com.portfolio.backend.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.backend.security.DAO.UserDAO;
import com.portfolio.backend.security.model.LoginUser;

import java.util.Optional;

@Service
@Transactional
public class UserAuthService {

    @Autowired
    UserDAO userDAO;

    public Optional<LoginUser> getByUserName(String userName){
        return userDAO.findByUserName(userName);
    }

    public boolean existsByUserName(String userName){
        return userDAO.existsByUserName(userName);
    }

    public boolean existsByEmail(String email){
        return userDAO.existsByEmail(email);
    }

    public void save(LoginUser user){
        userDAO.save(user);
    }
}
