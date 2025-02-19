package com.testmomoto.spring.testmomoto.web.Models;

import java.util.List;

import com.testmomoto.spring.testmomoto.Api.Models.PokemonTypes;


public class TypesViewModel {
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
