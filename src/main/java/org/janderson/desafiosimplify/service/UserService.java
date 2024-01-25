package org.janderson.desafiosimplify.service;

import org.janderson.desafiosimplify.Repository.UserRepository;
import org.janderson.desafiosimplify.dto.users.UserDto;
import org.janderson.desafiosimplify.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void saveNewUser(User saveUser){
        saveUser.setPassword(passwordEncoder().encode(saveUser.getPassword()));
        userRepository.save(saveUser);
    }
    public void saveUpdateUser(User putUser){
        userRepository.save(putUser);
    }

    public User fromDto(UserDto userDto){
        return new User(userDto.email(), userDto.name(),userDto.password());
    }


    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public User serchUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByEmail(auth.toString());
    }

}
