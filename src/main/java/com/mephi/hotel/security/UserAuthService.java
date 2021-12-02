package com.mephi.hotel.security;

import com.mephi.hotel.model.User;
import com.mephi.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("CustomUserDetailsService")
public class UserAuthService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserAuthService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> user = Optional.ofNullable(repository.findUserByLogin(name));

        user.orElseThrow(() ->
                new UsernameNotFoundException("Пользователь " + name +" не найден!"));

        return user.map(MyUserDetails::new).get();

    }

}
