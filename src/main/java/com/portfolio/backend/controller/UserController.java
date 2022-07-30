package com.portfolio.backend.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.backend.DTO.Message;
import com.portfolio.backend.model.UserData;
import com.portfolio.backend.service.UserDataService;


@RestController
@CrossOrigin
public class UserController {
    
    @Autowired
    UserDataService userService;

     
    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<UserData>> getUserDataName(@PathVariable String userId){
    
        Optional<UserData> selectedUser;

        if (Character.isDigit(userId.charAt(0))){
            selectedUser = userService.getDataById(Integer.parseInt(userId));
        }else{
            selectedUser = userService.getDataByUserName(userId);
        }
        if (selectedUser.isEmpty()){
            return new ResponseEntity(new Message("No existe el usuario"), HttpStatus.NOT_FOUND);    
        }
        return new ResponseEntity<Optional<UserData>>(selectedUser, HttpStatus.OK);
    }
}
