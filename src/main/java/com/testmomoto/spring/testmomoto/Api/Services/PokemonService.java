package com.testmomoto.spring.testmomoto.Api.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.testmomoto.spring.testmomoto.Api.Models.JsonResponse;
import com.testmomoto.spring.testmomoto.Api.Models.JsonTypesResponse;
import com.testmomoto.spring.testmomoto.Api.Models.Pokemon;
import com.testmomoto.spring.testmomoto.Api.Models.PokemonResponse;
import com.testmomoto.spring.testmomoto.Api.Models.PokemonTypes;
import com.testmomoto.spring.testmomoto.Api.Models.TeamPokemon;
import com.testmomoto.spring.testmomoto.Api.Models.Trainer;
import com.testmomoto.spring.testmomoto.Api.Models.Types;
import com.testmomoto.spring.testmomoto.Api.Models.TypesResponse;

@Service
public class PokemonService {
    private final RestTemplate restTemplate;

    //url base
    @Value("${poke.api}")
    private String urlbase;

    //listas para el retorno de los pokemones y tipos de pokemones
    public List<Pokemon> pokemons = new ArrayList<>();
    public List<Types> types = new ArrayList<>();

    //objetos para almacenar el entrenador y el equipo del entrenador
    public Trainer trainerDB = new Trainer();
    public List<TeamPokemon> teamPokemonDB = new ArrayList<>();

    //acumulador para ver la cantidad de pokemones repetidos
    public long acumulador = 0;

    @Autowired
    public PokemonService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    //Obtener objeto json con sus propiedades pokemon
    public JsonResponse getPokemonData() {
        //Obtener url completa de pokemon
        String url = urlbase + "pokemon/";
        
        //Consumir api de pokemon con su JSON respectivo
        JsonResponse response = restTemplate.getForObject(url, JsonResponse.class);
        return response;
    }

    //Obtener del objeto json los pokemones
    public List<Pokemon> getPokemonList(String search, String types){

        pokemons.clear();
        //Almacenar el JSON en una variable para acceder a sus propiedades
        JsonResponse pokemonJson = getPokemonData();

        //Obtener las propiedades del resultado
        List<PokemonResponse> pokemonList = pokemonJson.getResults();

        //Obtener toda la informacion de un pokemon
        for (PokemonResponse pokemon : pokemonList) {
            Pokemon pokemonDetails = restTemplate.getForObject(pokemon.getUrl(), Pokemon.class);
            pokemons.add(pokemonDetails);
        }

        // Si se proporciona un criterio de búsqueda, filtrar la lista
        if (search != null && !search.isEmpty()) {
            List<Pokemon> pokemonFilter = pokemons.stream()
            .filter(pokemon -> String.valueOf(pokemon.getId()).contains(search) || // Convertir ID a String y buscar
            pokemon.getName().toLowerCase().contains(search.toLowerCase())) // Buscar por nombre
            .toList();
            
            return  pokemonFilter;// Devuelve solo los Pokémon que coincidan con el criterio
        }

        // Si se proporciona un criterio de búsqueda, filtrar la lista
        if (types != null && !types.isEmpty()) {
            pokemons.clear();
            //Obtener url completa de pokemon
            String url = urlbase + "type/" + types + "/";

            //Consumir api de pokemon con su JSON respectivo
            Types response = restTemplate.getForObject(url, Types.class);

            @SuppressWarnings("null")
            List<PokemonTypes> pokemonTypes = response.getPokemon();

            for (PokemonTypes pokemonType : pokemonTypes) {
                PokemonResponse res = pokemonType.getPokemon();
                
                //Consumir api de pokemon con su JSON respectivo
                Pokemon pokemonRes = restTemplate.getForObject(res.getUrl(), Pokemon.class);
                pokemons.add(pokemonRes);
            }
        }

        return pokemons;
    }

    //Obtener objeto json con sus propiedades tipos
    public JsonTypesResponse getTypesData() {
        //Obtener url completa del tipo de pokemon
        String url = urlbase + "type/";
        
        //Consumir api de pokemon con su JSON respectivo
        JsonTypesResponse response = restTemplate.getForObject(url, JsonTypesResponse.class);
        return response;
    }

    //Obtener del objeto json los tipos de pokemones
    public List<Types> getTypesList(){

        types.clear();
        //Almacenar el JSON en una variable para acceder a sus propiedades
        JsonTypesResponse typesJson = getTypesData();

        //Obtener las propiedades del resultado
        List<TypesResponse> typesList = typesJson.getResults();

        //Obtener toda la informacion del tipo de pokemon
        for (TypesResponse typesRes : typesList) {
            Types typesDetails = restTemplate.getForObject(typesRes.getUrl(), Types.class);
            types.add(typesDetails);
        }

        return types;
    }

    //Obtener un pokemon para detalles
    public Pokemon getPokemon(String id){
        //Obtener url completa de pokemon
        String url = urlbase + "pokemon/" + id + "/";

        //Consumir api de pokemon con su JSON respectivo
        Pokemon response = restTemplate.getForObject(url, Pokemon.class);

        return response;
    }

    //Crear un entrenador Pokemon
    public Trainer setTrainer(){
        Trainer trainer = new Trainer(1, "Erick Penado");
        trainerDB = trainer;
        return trainer;
    }

    //Obtener un entrenador
    public Trainer getTrainer(){
        return trainerDB;
    }

    //Obtener equipo relacionado a entrenador
    public List<TeamPokemon> getTeamPokemon(){
        return teamPokemonDB;
    }

    //Asignar un equipo
    public void setTeamPokemon(TeamPokemon teamPokemon){
        if(teamPokemonDB.size() > 6){
            System.out.println("No se puede agregar más de 6 Pokémon al equipo.");
            return;
        }
         // Contar cuántas veces se ha registrado el mismo Pokémon
        acumulador = teamPokemonDB.stream()
        .filter(p -> p.getPokemon().getName().contains(teamPokemon.getPokemon().getName()))
        .count();

        if (acumulador >= 3) {
            System.out.println("No se puede registrar más de 3 veces el mismo Pokémon: " + teamPokemon.getName());
            return;
        }
        
        teamPokemonDB.add(teamPokemon);
    }

    public List<TeamPokemon> setTeamPokemonList(){
        List<TeamPokemon> teamList = new ArrayList<>();
        teamList.add(new TeamPokemon(1, "Equipo Erick", getTrainer(),getPokemon("1")));
        teamList.add(new TeamPokemon(2, "Equipo Erick", getTrainer(),getPokemon("1")));
        teamList.add(new TeamPokemon(3, "Equipo Erick", getTrainer(),getPokemon("1")));
        teamList.add(new TeamPokemon(4, "Equipo Erick", getTrainer(),getPokemon("1")));
        teamList.add(new TeamPokemon(5, "Equipo Erick", getTrainer(),getPokemon("20")));
        teamList.add(new TeamPokemon(6, "Equipo Erick", getTrainer(),getPokemon("19")));
        teamList.add(new TeamPokemon(7, "Equipo Erick", getTrainer(),getPokemon("17")));

        for (TeamPokemon team : teamList) {
            setTeamPokemon(team);
        }
        return teamList;
    }
}
