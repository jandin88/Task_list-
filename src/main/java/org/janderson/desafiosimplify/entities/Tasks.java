package org.janderson.desafiosimplify.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.janderson.desafiosimplify.dto.tasks.TasksRequestDto;
import org.janderson.desafiosimplify.entities.enuns.Priority;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;

@Document
public class Tasks implements Serializable {
    @Id
    private String id;
    private String title;
    private String description;
    private Priority priority;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern ="dd-MM-yyyy HH:mm",timezone = "GMT-3")
    private Instant creatTask;

    @DBRef
    private User user;

    public Tasks(TasksRequestDto tasksDto,  User user) {
    this.title = tasksDto.name();
    this.description = tasksDto.description();
    this.priority = tasksDto.priority();
    this.user = user;
    this.creatTask = Instant.now();
    }

    public Tasks(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Instant getCreatTask() {
        return creatTask;
    }

    public void setCreatTask(Instant creatTask) {
        this.creatTask = creatTask;
    }

    public User getUser() {
        return user;
    }


}
