package com.mephi.hotel.controller;

import com.mephi.hotel.model.User;
import com.mephi.hotel.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

//    @Transactional
    @GetMapping("/user/{idUser}")
    public Map<String, Object> getUserData(@PathVariable Long idUser) {
        User user = userService.findUserById(idUser);

        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return map;
    }
}
