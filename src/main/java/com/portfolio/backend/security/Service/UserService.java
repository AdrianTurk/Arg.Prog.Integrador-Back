package com.portfolio.backend.security.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.backend.security.DAO.IUserDAO;
import com.portfolio.backend.security.entity.User;

@Service
@Transactional
public class UserService {
    @Autowired
    IUserDAO userDAO;

    public Optional<User> getByUserName(String userName){
        return userDAO.findByUserName(userName);
    }
    
    public boolean existsByUserName(String userName){
        return userDAO.existsByUserName(userName);
    }

    public boolean existsByUserEmail(String userEmail){
        return userDAO.existsByUserEmail(userEmail);
    }
    
    public void save(User newUser){
        userDAO.save(newUser);
    }
}