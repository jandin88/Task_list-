package org.janderson.desafiosimplify.controllers;

import jakarta.validation.Valid;
import org.janderson.desafiosimplify.dto.tasks.TasksRequestDto;
import org.janderson.desafiosimplify.dto.tasks.TasksResponseDto;
import org.janderson.desafiosimplify.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TasksController {


    @Autowired
    private TasksService service;

    @GetMapping
    public ResponseEntity<List<TasksResponseDto>> findAll(){
        return ResponseEntity.ok().body(service.findAllTaskUser());
    }

    @GetMapping("{name}")
    public ResponseEntity<TasksResponseDto>findByName(@PathVariable String name){
        return ResponseEntity.ok().body(service.findByName(name));
    }

    @PostMapping("new")
    public ResponseEntity<TasksResponseDto>createdTasks(@RequestBody @Valid TasksRequestDto tasks) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createdTask(tasks));

    }

    @DeleteMapping("{name}")
    public ResponseEntity<TasksResponseDto> deletedTasks(@PathVariable String name){
        service.deletedTasks(name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{name}")
    public ResponseEntity<TasksResponseDto>updateTasks(@PathVariable String name, @RequestBody TasksRequestDto tasks){
       service.updateTasks(tasks,name);
       return ResponseEntity.noContent().build();
    }


}
