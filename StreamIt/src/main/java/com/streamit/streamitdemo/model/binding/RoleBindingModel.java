package com.streamit.streamitdemo.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

public class RoleBindingModel {

    private Long id;
    private String name;

    public RoleBindingModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Length(min = 2, message = "Name length must be at least two characters")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
