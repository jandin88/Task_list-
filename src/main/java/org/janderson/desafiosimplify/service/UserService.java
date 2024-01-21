package org.janderson.desafiosimplify.service;

import org.janderson.desafiosimplify.Repository.UserRepository;
import org.janderson.desafiosimplify.dto.users.UserDto;
import org.janderson.desafiosimplify.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveNewUser(User saveUSer){
       return userRepository.save(saveUSer);
    }

    public User fromDto(UserDto userDto){
        return new User(userDto.email(), userDto.name(),userDto.password());
    }

    public UserDto toDto(User user){
        return new UserDto(user.getEmail(), user.getName(),user.getPassword());
    }
}
