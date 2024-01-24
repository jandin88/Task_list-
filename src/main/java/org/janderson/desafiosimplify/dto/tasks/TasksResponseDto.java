package org.janderson.desafiosimplify.dto.tasks;


import org.janderson.desafiosimplify.entities.Tasks;
import org.janderson.desafiosimplify.entities.enuns.Priority;

import java.time.Instant;
import java.util.List;

public record TasksResponseDto(String name, String description, String realized, Priority priority, Instant creatTask)
        {

            public TasksResponseDto(Tasks tasks){
                this(tasks.getTitle(),tasks.getDescription(),tasks.getRealized(),tasks.getPriority(),tasks.getCreatTask());
            }


        }
