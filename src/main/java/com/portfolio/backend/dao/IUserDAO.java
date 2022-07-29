
package com.portfolio.backend.dao;

import com.portfolio.backend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDAO extends JpaRepository<Person,Long> {

}
