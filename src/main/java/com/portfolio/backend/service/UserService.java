
package com.portfolio.backend.service;

import com.portfolio.backend.dao.IUserDAO;
import com.portfolio.backend.interfaces.IUserService;
import com.portfolio.backend.model.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired IUserDAO userDAO;
    
    @Override
    public List<Person> getUsers() {
        List<Person> userList = userDAO.findAll();
        return userList;
    }

    @Override
    public void newUser(Person user) {
        userDAO.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public Person findUser(Long id) {
        return (Person)userDAO.findById(id).orElse(null);
    }
    
}
