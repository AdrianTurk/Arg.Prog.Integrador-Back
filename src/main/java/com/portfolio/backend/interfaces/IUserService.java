package com.portfolio.backend.interfaces;

import com.portfolio.backend.model.Person;
import java.util.List;


public interface IUserService{
    public List<Person> getUsers();
    public void newUser(Person user);
    public void deleteUser(Long id);
    public Person findUser(Long id);
}
