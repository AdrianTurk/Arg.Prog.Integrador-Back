package com.portfolio.backend.security.controller;

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
import org.springframework.web.bind.annotation.*;

import com.portfolio.backend.DTO.Message;
import com.portfolio.backend.security.DTO.JwtDTO;
import com.portfolio.backend.security.DTO.UserLogin;
import com.portfolio.backend.security.DTO.NewUser;
import com.portfolio.backend.security.enums.RoleFlag;
import com.portfolio.backend.security.jwt.JwtProvider;
import com.portfolio.backend.security.model.LoginRole;
import com.portfolio.backend.security.model.LoginUser;
import com.portfolio.backend.security.service.RoleService;
import com.portfolio.backend.security.service.UserAuthService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    /**
     *
     */
    private static final String MSG_ADDED = "Usuario guardado";
    private static final String MSG_EMAIL_ALREADY_EXIST = "No se puede dar de alta, el email ya existe. ";
    private static final String MSG_USER_ALREADY_EXIST = "No se puede dar de alta, el usuario ya existe.";
    private static final String MSG_WRONG_FIELD = "Campos invalidos.";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserAuthService usuarioService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message(MSG_WRONG_FIELD), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByUserName(nuevoUsuario.getUserName()))
            return new ResponseEntity(new Message(MSG_USER_ALREADY_EXIST), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Message(MSG_EMAIL_ALREADY_EXIST), HttpStatus.BAD_REQUEST);
        LoginUser usuario =
                new LoginUser(nuevoUsuario.getName(), nuevoUsuario.getUserName(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<LoginRole> roles = new HashSet<>();
        roles.add(roleService.getByRoleFlag(RoleFlag.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(roleService.getByRoleFlag(RoleFlag.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Message(MSG_ADDED), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody UserLogin loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message(MSG_WRONG_FIELD), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUserName(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
