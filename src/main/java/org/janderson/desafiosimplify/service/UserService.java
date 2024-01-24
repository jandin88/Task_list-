package org.janderson.desafiosimplify.service;

import org.janderson.desafiosimplify.Repository.UserRepository;
import org.janderson.desafiosimplify.dto.users.UserDto;
import org.janderson.desafiosimplify.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findByName(id);
    }
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

}
