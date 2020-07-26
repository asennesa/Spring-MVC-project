package com.streamit.streamitdemo.model.binding;

import com.streamit.streamitdemo.model.entity.Song;
import com.streamit.streamitdemo.model.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

public class PlaylistBindingModel {

    private String name;
    private String description;


    public PlaylistBindingModel() {
    }


    @Length(min = 2, message = "Name length must be at least two characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
