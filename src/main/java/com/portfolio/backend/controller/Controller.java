
package com.portfolio.backend.controller;

import com.portfolio.backend.interfaces.IUserService;
import com.portfolio.backend.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
 
    private static final String USERS_GET_PATH = "users/get";
    private static final String USER_DEL_ID_PATH = "user/del/{id}";
    private static final String USER_NEW_PATH = "user/new";
    private static final String USER_CHANGE_ID_PATH = "User/change/{id}";
    private static final String USER_GET_ID_PATH = "user/get/{id}";
    
    @Autowired IUserService userService;

    @GetMapping(USERS_GET_PATH)
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(USER_GET_ID_PATH)
    public User getUser(@PathVariable Long id){
        return userService.findUser(id);
    }

    @PutMapping(USER_CHANGE_ID_PATH)
    public String changeUser(@PathVariable Long id,
                             @RequestBody User userData){
        User placeHolder = userService.findUser(id);
        placeHolder.setId(userData.getId());
        placeHolder.setName(userData.getName());
        //TODO: Terminar de implementar ALguna forma mas directa?
        userService.newUser(placeHolder);
        return "User NOT FULLY CHANGED - Service not full implemented";
    }
    
    @PostMapping(USER_NEW_PATH)
    public String newUser(@RequestBody User user){
        userService.newUser(user);
        return "User ADDED";
    }
    
    @DeleteMapping(USER_DEL_ID_PATH)
    public String delUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User DELETED";
    }
}
