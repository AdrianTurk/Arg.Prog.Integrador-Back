package com.portfolio.backend.security.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.backend.security.model.LoginUser;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<LoginUser, Integer> {
    Optional<LoginUser> findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

}
