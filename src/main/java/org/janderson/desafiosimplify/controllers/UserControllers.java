package org.janderson.desafiosimplify.controllers;

import org.janderson.desafiosimplify.dto.users.UserDto;
import org.janderson.desafiosimplify.entities.User;
import org.janderson.desafiosimplify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserControllers {

    @Autowired
    private UserService service;

    @PostMapping("register")
    public ResponseEntity<Void> insertUser(@RequestBody UserDto newUser){
        User user= service.fromDto(newUser);
        service.saveNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
