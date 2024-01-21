package org.janderson.desafiosimplify.controllers;

import org.janderson.desafiosimplify.entities.Tasks;
import org.janderson.desafiosimplify.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TasksController {


    @Autowired
    private TasksService service;

    @GetMapping
    public ResponseEntity<List<Tasks>>findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
    @GetMapping("{name}")
    public ResponseEntity<Tasks>findByName(@PathVariable String name){
        return ResponseEntity.ok().body(service.findByName(name));
    }

    @PostMapping("new")
    public ResponseEntity<Tasks>createdTasks(@RequestBody Tasks tasks){
        Tasks newTask= service.createdTask(tasks);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}").buildAndExpand(newTask.getName()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping("{name}")
    public ResponseEntity<Tasks> deletedTasks(@PathVariable String name){
        service.deletedTasks(name);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{name}")
    public ResponseEntity<Tasks>updateTasks(@PathVariable String name, @RequestBody Tasks tasks){
       service.updateTasks(tasks,name);
        return ResponseEntity.noContent().build();
    }


}
