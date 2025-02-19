package com.testmomoto.spring.testmomoto.Api.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PokemonTypes {
    public PokemonResponse pokemon;
    public int slot;

    
    public int getSlot() {
        return slot;
    }
    public void setSlot(int slot) {
        this.slot = slot;
    }
    public PokemonResponse getPokemon() {
        return pokemon;
    }
    public void setPokemon(PokemonResponse pokemon) {
        this.pokemon = pokemon;
    }
    
}
