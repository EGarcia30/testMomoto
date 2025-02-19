package com.testmomoto.spring.testmomoto.web.Models;

import com.testmomoto.spring.testmomoto.Api.Models.Pokemon;
import com.testmomoto.spring.testmomoto.Api.Models.Trainer;

public class TeamPokemonViewModel {
    public int id;
    public String name;
    public Trainer trainer;
    public Pokemon pokemon;
    
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
