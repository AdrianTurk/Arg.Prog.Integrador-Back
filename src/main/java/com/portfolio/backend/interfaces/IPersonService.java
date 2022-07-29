package com.portfolio.backend.interfaces;

import com.portfolio.backend.model.Person;
import java.util.List;


public interface IPersonService{
    public List<Person> getPerson();
    public void newPerson(Person person);
    public void delPerson(Long id);
    public Person findPerson(Long id);
}
