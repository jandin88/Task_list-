package org.janderson.desafiosimplify.dto.users;


import org.janderson.desafiosimplify.entities.User;


public record UserDto(String email, String name, String password) {


    public UserDto(User user) {
        this(user.getEmail(), user.getName(), user.getPassword());
    }


}
