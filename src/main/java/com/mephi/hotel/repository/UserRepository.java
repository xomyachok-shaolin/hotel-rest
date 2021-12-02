package com.mephi.hotel.repository;

import com.mephi.hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLogin(String s);
    Long countUserByEmail(String email);
    Long countUserByLogin(String login);


    @Query("select u from User u where u.login = ?1")
    User findUserByLoginCustomQuery(String name);

    @Query("select u from User u where u.email = ?1")
    User findUserByEmailCustomQuery(String email);
}
