package com.testmomoto.spring.testmomoto.Api.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Types {
    
    public int id;
    public String name;
    public List<PokemonTypes> pokemon;

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
    public List<PokemonTypes> getPokemon() {
        return pokemon;
    }
    public void setPokemon(List<PokemonTypes> pokemon) {
        this.pokemon = pokemon;
    }
}
