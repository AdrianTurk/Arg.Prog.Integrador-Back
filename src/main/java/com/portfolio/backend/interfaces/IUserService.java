package com.portfolio.backend.interfaces;

import com.portfolio.backend.model.User;
import java.util.List;


public interface IUserService{
    public List<User> getUsers();
    public void newUser(User user);
    public void deleteUser(Long id);
    public User findUser(Long id);
}
