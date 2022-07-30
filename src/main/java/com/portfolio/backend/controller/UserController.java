package com.portfolio.backend.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.backend.DTO.Message;
import com.portfolio.backend.DTO.UserDataDTO;
import com.portfolio.backend.model.UserData;
import com.portfolio.backend.security.service.UserAuthService;
import com.portfolio.backend.service.UserDataService;

@RestController
@CrossOrigin
public class UserController {
    /**
     *
     */
    private static final String MSG_ERR_USER_DATA_ALREADY_EXIST = "Los datos para ese usuario ya existen.";
    private static final String MSG_ERR_INVALID_FIELD_FORMAT = "No se respetaron las reglas de validacion: ";
    private static final String MSG_USER_DATA_UPDATED = "Datos modificados correctamente";
    private static final String MSG_USER_DATA_ADDED = "Datos de usuario creados correctamente";
    private static final String MSG_ERR_USER_NOT_EXIST = "No se pudo crear el set de datos, el usuario no existe";
    private static final String MSG_REQ_USER_NOT_EXIST = "No existe el usuario solicitado";

    @Autowired
    UserDataService userDataService;

    @Autowired
    UserAuthService userAuthService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<UserData>> getUserDataName(@PathVariable String userId) {

        Optional<UserData> selectedUser;

        if (Character.isDigit(userId.charAt(0))) {
            selectedUser = userDataService.getDataById(Integer.parseInt(userId));
        } else {
            selectedUser = userDataService.getDataByUserName(userId);
        }
        if (selectedUser.isEmpty()) {
            return new ResponseEntity(new Message(MSG_REQ_USER_NOT_EXIST), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<UserData>>(selectedUser, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUserData(@Valid @RequestBody UserData userData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(
                    new Message(MSG_ERR_INVALID_FIELD_FORMAT + bindingResult.getAllErrors().toString()),
                    HttpStatus.BAD_REQUEST);
        }
        if (!userAuthService.existsByUserName(userData.getUserName())) {
            return new ResponseEntity(new Message(MSG_ERR_USER_NOT_EXIST), HttpStatus.BAD_REQUEST);
        }
        if (userDataService.existsByUserName(userData.getUserName())) {
            return new ResponseEntity(new Message(MSG_ERR_USER_DATA_ALREADY_EXIST), HttpStatus.BAD_REQUEST);
        }
        userDataService.save(userData);
        return new ResponseEntity(new Message(MSG_USER_DATA_ADDED), HttpStatus.OK);
    }

    @PutMapping("/update/{userName}")
    public ResponseEntity<?> updateUserData(@PathVariable String userName, @Valid @RequestBody UserDataDTO userData,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Message(MSG_ERR_INVALID_FIELD_FORMAT + bindingResult.getAllErrors()),
                    HttpStatus.BAD_REQUEST);
        }
        UserData destData;

        if (userAuthService.existsByUserName(userName)) {
            if (userDataService.existsByUserName(userName)) {
                destData = userDataService.getDataByUserName(userName).get();
            } else {
                destData = new UserData(userName);
            }

            UserData oldData = new UserData();
            modelMapper.map(destData, oldData);
            modelMapper.map(userData, destData);

            destData.setUserName(oldData.getUserName());
            destData.setId(oldData.getId());

            userDataService.save(destData);
            return new ResponseEntity(new Message(MSG_USER_DATA_UPDATED), HttpStatus.OK);
        }
        return new ResponseEntity(new Message(MSG_ERR_USER_NOT_EXIST + ": " + userName), HttpStatus.BAD_REQUEST);
    }
    // TODO: Implement Deletions
    // @DeleteMapping("/delete/{userName}")
    // public ResponseEntity<?> delUserData(@PathVariable String userName,
    // @RequestBody UserDataDTO userData){

    // return new ResponseEntity(new Message(MSG_ERR_USER_NOT_EXIST),
    // HttpStatus.BAD_REQUEST);
    // }
}
