
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
    @Autowired IUserService userService;
    
    @GetMapping("users/get")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("user/get/{id}")
    public User getUser(@PathVariable Long id){
        return userService.findUser(id);
    }


    @PutMapping("User/change/{id}")
    public String changeUser(@PathVariable Long id,
                             @RequestBody User userData){
        User placeHolder = userService.findUser(id);
        placeHolder.setId(userData.getId());
        placeHolder.setName(userData.getName());
        //TODO: Terminar de implementar ALguna forma mas directa?
        userService.newUser(placeHolder);
        return "User NOT FULLY CHANGED - Service not full implemented";
    }
    
    @PostMapping("user/new")
    public String newUser(@RequestBody User user){
        userService.newUser(user);
        return "User ADDED";
    }
    
    @DeleteMapping("user/del/{id}")
    public String delUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User DELETED";
    }
    

}
