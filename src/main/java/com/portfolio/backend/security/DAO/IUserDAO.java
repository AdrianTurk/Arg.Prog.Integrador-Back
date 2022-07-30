package com.portfolio.backend.security.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.backend.security.entity.User;



@Repository
public interface IUserDAO extends JpaRepository<User, Integer>{
    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);
    boolean existsByUserEmail(String userEmail);
}
