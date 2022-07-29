
package com.portfolio.backend.controller;

import com.portfolio.backend.interfaces.IPersonService;
import com.portfolio.backend.model.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class Controller {
 
    private static final String USERS_GET_PATH = "users/get";
    private static final String USER_DEL_ID_PATH = "user/del/{id}";
    private static final String USER_NEW_PATH = "user/new";
    private static final String USER_CHANGE_ID_PATH = "User/change/{id}";
    private static final String USER_GET_ID_PATH = "user/get/{id}";
    
    @Autowired IPersonService userService;

    @GetMapping(USERS_GET_PATH)
    public List<Person> getPerson(){
        return userService.getPerson();
    }

    @GetMapping(USER_GET_ID_PATH)
    public Person getPerson(@PathVariable Long id){
        return userService.findPerson(id);
    }

    @PutMapping(USER_CHANGE_ID_PATH)
    public String changePerson(@PathVariable Long id,
                             @RequestBody Person userData){
        Person placeHolder = userService.findPerson(id);
        placeHolder.setId(userData.getId());
        placeHolder.setName(userData.getName());
        //TODO: Terminar de implementar ALguna forma mas directa?
        userService.newPerson(placeHolder);
        return "User NOT FULLY CHANGED - Service not full implemented";
    }
    
    @PostMapping(USER_NEW_PATH)
    public String newPerson(@RequestBody Person user){
        userService.newPerson(user);
        return "User ADDED";
    }
    
    @DeleteMapping(USER_DEL_ID_PATH)
    public String delPerson(@PathVariable Long id){
        userService.delPerson(id);
        return "User DELETED";
    }
}
