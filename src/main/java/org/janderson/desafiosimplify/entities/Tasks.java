package org.janderson.desafiosimplify.entities;


import org.janderson.desafiosimplify.entities.enuns.Priority;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Tasks implements Serializable {
    @Id
    private String name;
    private String descricao;
    private String realizado;
    private Priority priority;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descrição) {
        this.descricao = descrição;
    }

    public String getRealizado() {
        return realizado;
    }

    public void setRealizado(String realizado) {
        this.realizado = realizado;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }


}
