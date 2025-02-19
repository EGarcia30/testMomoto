package com.testmomoto.spring.testmomoto.Api.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testmomoto.spring.testmomoto.Api.Models.Pokemon;
import com.testmomoto.spring.testmomoto.Api.Models.TeamPokemon;
import com.testmomoto.spring.testmomoto.Api.Models.Trainer;
import com.testmomoto.spring.testmomoto.Api.Models.Types;
import com.testmomoto.spring.testmomoto.Api.Services.PokemonService;



@RestController
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;


    //Obtener la lista de Pokemones
    @GetMapping("/pokemon")
    public List<Pokemon> getPokemon(@RequestParam(required = false) String search, @RequestParam(required = false) String types) {
        Trainer trainer = pokemonService.getTrainer();
        if(trainer.getId() == 0){
            pokemonService.setTrainer();
        }
        return pokemonService.getPokemonList(search, types);
    }
    
    //Obtener el tipo de pokemones
    @GetMapping("/typesPokemon")
    public List<Types> getTypes() {
        return pokemonService.getTypesList();
    }

    //Obtener la informacion o el detalle de un pokemon
    @GetMapping("/details/pokemon/{id}")
    public Pokemon getDetails(@PathVariable String id) {
        return pokemonService.getPokemon(id);
    }

    //Obtener el entrenador
    @GetMapping("/trainer")
    public Trainer getTrainer() {
        Trainer trainer = pokemonService.getTrainer();
        if(trainer.getId() == 0){
            pokemonService.setTrainer();
        }
        return trainer;
    }
    
    //Obtener el equipo del entrenador
    @GetMapping("/details/teamPokemon")
    public List<TeamPokemon> getTeamPokemon() {
        List<TeamPokemon> team = pokemonService.getTeamPokemon();
        if(team.isEmpty()){
            pokemonService.setTeamPokemonList();
        }
        return pokemonService.getTeamPokemon();
    }
    
}
