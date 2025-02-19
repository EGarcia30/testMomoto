package com.testmomoto.spring.testmomoto.Api.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Pokemon {
    public int id;
    public String name;
    public List<Object> types;
    public sprites sprites;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public sprites getSprites() {
        return sprites;
    }
    public void setSprites(sprites sprites) {
        this.sprites = sprites;
    }
    public List<Object> getTypes() {
        return types;
    }
    public void setTypes(List<Object> types) {
        this.types = types;
    }
    
}
