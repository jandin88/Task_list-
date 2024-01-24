package org.janderson.desafiosimplify.service;

import org.janderson.desafiosimplify.Repository.TasksRepository;
import org.janderson.desafiosimplify.dto.tasks.TasksRequestDto;
import org.janderson.desafiosimplify.dto.tasks.TasksResponseDto;
import org.janderson.desafiosimplify.entities.Tasks;
import org.janderson.desafiosimplify.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TasksService {

    @Autowired
    private TasksRepository repository;
    @Autowired
    private UserService userService;

    public List<TasksResponseDto> findAllTaskUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        User user = userService.findByEmail(auth);
        List<Tasks> tasksList = repository.findAllByUserId(user.getId());
        List<TasksResponseDto> responseList = new ArrayList<>();

        for (Tasks tasks : tasksList) {
            responseList.add(new TasksResponseDto(tasks));
        }

        return responseList;
    }
    public Tasks findByName(String name){
        return repository.findByTitle(name);
    }

    public TasksResponseDto createdTask(TasksRequestDto tasksDto){
        var auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByEmail(auth.toString());
        Tasks newTask = new Tasks(tasksDto, user);
        Tasks responseTask = repository.save(newTask);
        user.addTask(newTask);
        userService.saveUpdateUser(user);
        return new TasksResponseDto(responseTask);
    }

    public void deletedTasks(String name){
        repository.deleteById(name);
    }

    public void updateTasks(Tasks tasks, String name){
        Tasks obj =findByName(name);
        updateData(tasks,obj);
        repository.save(tasks);
    }

    private void updateData(Tasks tasks, Tasks obj) {
        obj.setTitle(tasks.getTitle());
        obj.setDescription(tasks.getDescription());
        obj.setPriority(tasks.getPriority());
    }
}
