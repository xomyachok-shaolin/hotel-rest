package com.mephi.hotel.service;

import com.mephi.hotel.model.User;
import com.mephi.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByLogin(Long idUser, String name){
        return userRepository.findUserByLoginCustomQuery( name);
    }

    public User findUserByEmail(Long idUser, String email) {
        return userRepository.findUserByEmailCustomQuery(email);
    }


    public Long countUserByLogin(String login){ return userRepository.countUserByLogin(login);}
    public Long countUserByEmail(String email) {
        return userRepository.countUserByEmail(email);
    }
    public User findUserById(Long idUser) {
        return  userRepository.findById(idUser).get();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
