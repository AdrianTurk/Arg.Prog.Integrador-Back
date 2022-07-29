
package com.portfolio.backend.service;

import com.portfolio.backend.dao.IPersonDAO;
import com.portfolio.backend.interfaces.IPersonService;
import com.portfolio.backend.model.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {
    @Autowired IPersonDAO personDAO;
    
    @Override
    public List<Person> getPerson() {
        List<Person> userList = personDAO.findAll();
        return userList;
    }

    @Override
    public void newPerson(Person user) {
        personDAO.save(user);
    }

    @Override
    public void delPerson(Long id) {
        personDAO.deleteById(id);
    }

    @Override
    public Person findPerson(Long id) {
        return (Person)personDAO.findById(id).orElse(null);
    }
    
}
