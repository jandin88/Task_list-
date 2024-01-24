package org.janderson.desafiosimplify.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document
public class User {


    @Id
    private String id;
    private String email;
    private String name;
    private String password;

    @DBRef
    private List<Tasks> tasks;


    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }
    public void addTask(Tasks task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
    }
    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    public String getId() {
        return id;
    }
}
