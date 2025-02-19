package com.testmomoto.spring.testmomoto.Api.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamPokemon {

    public int id;
    public String name;
    public Trainer trainer;
    public Pokemon pokemon;
    

    
    public TeamPokemon(int id, String name, Trainer trainer, Pokemon pokemon) {
        this.id = id;
        this.name = name;
        this.trainer = trainer;
        this.pokemon = pokemon;
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
    public Pokemon getPokemon() {
        return pokemon;
    }
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
    public Trainer getTrainer() {
        return trainer;
    }
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
    

}
