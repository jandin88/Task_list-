package org.janderson.desafiosimplify.service;

import org.janderson.desafiosimplify.Repository.TasksRepository;
import org.janderson.desafiosimplify.entities.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TasksService {

    @Autowired
    private TasksRepository repository;

    public List<Tasks> findAll(){
        return repository.findAll();
    }
    public Tasks findByName(String name){
        return repository.findById(name).orElseThrow(()->new NoSuchElementException("Name not found "+name));
    }

    public Tasks createdTask(Tasks tasks){
        return repository.save(tasks);
    }

    public void deletedTasks(String name){
        repository.deleteById(name);
    }

    public Tasks updateTasks(Tasks tasks, String name){
        Tasks obj =findByName(name);
        updateDAta(tasks,obj);
        return repository.save(tasks);
    }

    private void updateDAta(Tasks tasks, Tasks obj) {
        obj.setName(tasks.getName());
        obj.setDescricao(tasks.getDescricao());
        obj.setPriority(tasks.getPriority());
        obj.setRealizado(tasks.getRealizado());
    }
}
