package com.testmomoto.spring.testmomoto.web.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.testmomoto.spring.testmomoto.web.Models.PokemonViewModel;
import com.testmomoto.spring.testmomoto.web.Models.TeamPokemonViewModel;
import com.testmomoto.spring.testmomoto.web.Models.TrainerViewModel;
import com.testmomoto.spring.testmomoto.web.Models.TypesViewModel;


@Controller
public class HomeController {


    @Value("${rest.api}")
    public String urlBase;

    public RestTemplate restTemplate;
    
    @Autowired
    public HomeController(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }
    
    @GetMapping("/")
    public String home(@RequestParam(required=false) String search, @RequestParam(required=false) String types, Model modelo) {

        //url de pokemones;
        String urlPokemon = urlBase + "pokemon";
        String urlTypes = urlBase + "typesPokemon";
        
        // Construir la URL con los parámetros 'search' y 'type' para Pokémon
        UriComponentsBuilder builderPokemon = UriComponentsBuilder.fromHttpUrl(urlPokemon)
            .queryParam("search", search)
            .queryParam("types", types);

            String urlPokemonWithParams = builderPokemon.toUriString();

        // Obtener la lista de Pokémon desde el servicio
        ResponseEntity<List<PokemonViewModel>> pokemonAPI = restTemplate.exchange(
            urlPokemonWithParams,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<PokemonViewModel>>() {}
        );

         // Obtener la lista de tipó de Pokémon desde el servicio
            ResponseEntity<List<TypesViewModel>> typesAPI = restTemplate.exchange(
            urlTypes,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<TypesViewModel>>() {}
        );

        //Obtener las listas de pokemones y tipos de pokemones
        List<PokemonViewModel> pokemones = pokemonAPI.getBody();
        List<TypesViewModel> pokemonesTypes = typesAPI.getBody();

        //asignar al modelo los atributos
        modelo.addAttribute("pokemones", pokemones);
        modelo.addAttribute("pokemonesTypes",pokemonesTypes);
        return "index";
    }

    @GetMapping("/details/{id}")
    public String detailsPokemon(@PathVariable String id, Model modelo) {

        //url de detalle de pokemones;
        String urlDetailsPokemon = urlBase + "details/pokemon/" + id;

        // Construir la URL con los parámetros 'id' para Pokémon
        UriComponentsBuilder builderDetailsPokemon = UriComponentsBuilder.fromHttpUrl(urlDetailsPokemon)
            .queryParam("id", id);

            String urlDetailsPokemonWithParams = builderDetailsPokemon.toUriString();

        // Obtener el detalle del Pokémon desde el servicio
        PokemonViewModel detailsPokemonAPI = restTemplate.getForObject(urlDetailsPokemonWithParams, PokemonViewModel.class );

        modelo.addAttribute("pokemon", detailsPokemonAPI);
        return "details";
    }
    
    @GetMapping("/details/trainer")
    public String detailsTrainer(Model modelo) {

        //url de detalle de pokemones;
        String urlDetailsTrainer = urlBase + "trainer";
        String urlDetailsTeamPokemon = urlBase + "details/teamPokemon";

       // Obtener el detalle del Pokémon desde el servicio
        TrainerViewModel detailsTrainerAPI = restTemplate.getForObject(urlDetailsTrainer, TrainerViewModel.class );

        // Obtener la lista de equipo del entrenador desde el servicio
        ResponseEntity<List<TeamPokemonViewModel>> teamPokemonAPI = restTemplate.exchange(
            urlDetailsTeamPokemon,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<TeamPokemonViewModel>>() {}
        );

        //Obtener las listas de pokemones y tipos de pokemones
        List<TeamPokemonViewModel> teamPokemonList = teamPokemonAPI.getBody();

        modelo.addAttribute("trainer", detailsTrainerAPI);
        modelo.addAttribute("teamPokemonDetail", teamPokemonList);
        return "trainer";
    }
}
