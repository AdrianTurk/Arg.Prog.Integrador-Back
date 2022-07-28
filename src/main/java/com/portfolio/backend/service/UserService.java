
package com.portfolio.backend.service;

import com.portfolio.backend.dao.IUserDAO;
import com.portfolio.backend.interfaces.IUserService;
import com.portfolio.backend.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired IUserDAO userDAO;
    
    @Override
    public List<User> getUsers() {
        List<User> userList = userDAO.findAll();
        return userList;
    }

    @Override
    public void newUser(User user) {
        userDAO.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public User findUser(Long id) {
        return (User)userDAO.findById(id).orElse(null);
    }
    
}
