package com.portfolio.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.backend.model.UserData;


@Repository
public interface IUserDAO extends JpaRepository<UserData,Long> {
    Optional<UserData> findByUserName(String name);
    Optional<UserData> findById(long id);

    boolean existsByUserName(String name);
    boolean existsById(long id);
}
