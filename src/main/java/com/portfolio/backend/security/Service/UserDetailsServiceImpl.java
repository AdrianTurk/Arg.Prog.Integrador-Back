package com.portfolio.backend.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portfolio.backend.security.model.LoginUser;
import com.portfolio.backend.security.model.MainLoginUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserAuthService userAuthService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        LoginUser user = userAuthService.getByUserName(userName).get();
        return MainLoginUser.build(user);
    }
}
