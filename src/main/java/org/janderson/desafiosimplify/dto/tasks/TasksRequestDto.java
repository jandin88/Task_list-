package org.janderson.desafiosimplify.dto.tasks;


import jakarta.validation.constraints.NotBlank;
import org.janderson.desafiosimplify.entities.enuns.Priority;

public record TasksRequestDto(
        @NotBlank(message = "insert title" )
        String title,
        @NotBlank(message = "insert description")
        String description,
        Priority priority) { }
