package com.testmomoto.spring.testmomoto.web.Models;

import java.util.List;

import com.testmomoto.spring.testmomoto.Api.Models.sprites;

public class PokemonViewModel {
    public int id;
    public String name;
    public List<Object> types;
    public sprites sprites;

    

    public PokemonViewModel() {
    }
    

    public PokemonViewModel(int id, String name, List<Object> types,
            com.testmomoto.spring.testmomoto.Api.Models.sprites sprites) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.sprites = sprites;
    }

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
