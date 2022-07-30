package com.portfolio.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.backend.dao.IUserDAO;
import com.portfolio.backend.model.UserData;

@Service
@Transactional
public class UserDataService {
    
    @Autowired
    IUserDAO userDAO;
    
    public Optional<UserData> getDataById(long id){
        return userDAO.findById(id);
    }

    public Optional<UserData> getDataByUserName(String loginName){
        return userDAO.findByUserName(loginName);
    }
    

    public boolean existsById(long id){
        return userDAO.existsById(id);
    }
    
    public boolean existsByUserName(String name){
        return userDAO.existsByUserName(name);
    }

    public void save(UserData data){
        userDAO.save(data);
    }
    
    public void delete(long id){
        userDAO.deleteById(id);
    }
}
