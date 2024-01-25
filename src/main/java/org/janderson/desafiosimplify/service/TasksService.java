package org.janderson.desafiosimplify.service;

import org.janderson.desafiosimplify.Repository.TasksRepository;
import org.janderson.desafiosimplify.Exception.TaskElementExist;
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
        List<TasksResponseDto>tasksResponse=findTaskAllByUser();
        if(!tasksResponse.isEmpty()) {
            return tasksResponse;
        }else {
            throw new NoSuchElementException("User task not found");
        }
    }
    public TasksResponseDto findByName(String name){
        User user= userService.serchUser();
        Tasks tasksList= repository.findByTitleIgnoreCaseAndUser(name,user);
        if(tasksList!=null) {
            return new TasksResponseDto(tasksList);
        }
        else {
            throw new NoSuchElementException(name + " element not found");
        }
    }

    public TasksResponseDto createdTask(TasksRequestDto tasksDto) {
        User user= userService.serchUser();
        Tasks newTask = new Tasks(tasksDto, user);
        if(CheckExistTask(tasksDto)) {
            Tasks responseTask = repository.save(newTask);
            user.addTask(newTask);
            userService.saveUpdateUser(user);
            return new TasksResponseDto(responseTask);
        }else {
            throw new TaskElementExist("task element exists");
        }
    }


    public void deletedTasks(String name){
        TasksResponseDto tasks=findByName(name);
        repository.deleteByTitle(tasks.title());
    }

    public void updateTasks(TasksRequestDto tasks, String name){
        User user= userService.serchUser();
        Tasks obj =  repository.findByTitleIgnoreCaseAndUser(name,user);
        if(obj!=null) {
            updateData(tasks, obj);
            repository.save(obj);
        }else {
            throw new NoSuchElementException(name +" element not found");
        }
    }


    private List<TasksResponseDto> findTaskAllByUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.findByEmail(auth);
        List<Tasks> tasksList = repository.findAllByUserId(user.getId());
        List<TasksResponseDto> responseList = new ArrayList<>();
        for (Tasks tasks : tasksList) {
            responseList.add(new TasksResponseDto(tasks));
        }
        return responseList;
    }

    private boolean CheckExistTask(TasksRequestDto tasks) {
        List<TasksResponseDto> tasksResponseDto= findTaskAllByUser();
        for(TasksResponseDto response : tasksResponseDto){
            if(Objects.equals(response.title(), tasks.title())){
                return false;
            }
        }
        return true;
    }


    private void updateData(TasksRequestDto tasks, Tasks obj) {
        obj.setTitle(tasks.title());
        obj.setDescription(tasks.description());
        obj.setPriority(tasks.priority());
    }



//    private List<TasksResponseDto> listTasks(List<Tasks> tasksList) {
//        List<TasksResponseDto> tasksResponse = new ArrayList<>();
//        for (Tasks tasks : tasksList) {
//            tasksResponse.add(new TasksResponseDto(tasks));
//        }
//        return tasksResponse;
//    }

}
