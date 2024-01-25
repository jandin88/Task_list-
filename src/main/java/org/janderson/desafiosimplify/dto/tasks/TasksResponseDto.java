package org.janderson.desafiosimplify.dto.tasks;


import org.janderson.desafiosimplify.entities.Tasks;
import org.janderson.desafiosimplify.entities.enuns.Priority;

import java.time.Instant;

public record TasksResponseDto(String title, String description, Priority priority, Instant creatTask)
        {

            public TasksResponseDto(Tasks tasks){
                this(tasks.getTitle(),tasks.getDescription(),tasks.getPriority(),tasks.getCreatTask());
            }


        }
