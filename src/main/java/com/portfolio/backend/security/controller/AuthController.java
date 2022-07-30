package com.portfolio.backend.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.backend.security.DTO.JwtDTO;
import com.portfolio.backend.security.DTO.NewUser;
import com.portfolio.backend.security.DTO.UserLogin;
import com.portfolio.backend.security.Service.RoleService;
import com.portfolio.backend.security.Service.UserService;
import com.portfolio.backend.security.entity.Role;
import com.portfolio.backend.security.entity.User;
import com.portfolio.backend.security.enums.RoleEnum;
import com.portfolio.backend.security.jwt.JwtProvider;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passEncoder;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> addUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Message("Datos ingresados incorrectos"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByUserName(newUser.getUserName())) {
            return new ResponseEntity<>(new Message("El usuario ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByUserEmail(newUser.getUserEmail())) {
            return new ResponseEntity<>(new Message("El email ya se encotraba registrado"), HttpStatus.BAD_REQUEST);
        }
        User user = new User(newUser.getUserName(),passEncoder.encode(newUser.getPassword()),
                             newUser.getName(),  newUser.getUserEmail());
                            Set<Role> roles = new HashSet<>();
                            
        roles.add(roleService.getByRole(RoleEnum.ROLE_USER).get());

        if (newUser.getRoles().contains("admin")) {
            roles.add(roleService.getByRole(RoleEnum.ROLE_ADMIN).get());
        }
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new Message("User agregado"), HttpStatus.CREATED);
    }

    @PostMapping("/login1")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody UserLogin userLogin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Message("campos no validos"), HttpStatus.BAD_REQUEST);
        }
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPass()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtProvider.generateToken(auth);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
    }
}
