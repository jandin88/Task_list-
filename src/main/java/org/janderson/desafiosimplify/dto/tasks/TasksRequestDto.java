package org.janderson.desafiosimplify.dto.tasks;


import org.janderson.desafiosimplify.entities.enuns.Priority;

public record TasksRequestDto(
        String name,

        String description,

        String realized,

        Priority priority) { }
