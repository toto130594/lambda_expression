package com.nttdata.prueba.controller;

import com.nttdata.prueba.exception.PokemonNotFoundException;
import com.nttdata.prueba.model.ErrorResponse;
import com.nttdata.prueba.model.Pokemon;
import com.nttdata.prueba.service.PokeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokeController {

  @Autowired
  private PokeService pokeService;

  @GetMapping("/pokemon/{name}")
  public Pokemon getPokemon(@PathVariable String name) {
    return pokeService.getPokemon(name);
  }

  @GetMapping("/pokemon")
  public List<Pokemon> getPokemonList() {
    return pokeService.getPokemonList();
  }

  @GetMapping("/battlePokemon/{pokemon1}/{pokemon2}")
  public String battlePokemon(@PathVariable String pokemon1, @PathVariable String pokemon2) {
    return pokeService.battlePokemon(pokemon1, pokemon2);
  }

  @GetMapping("/pokemonType/{type}")
  public List<String> getPokemonListType(@PathVariable String type) {
    return pokeService.getPokemonListType(type);
  }

  @GetMapping("/pokemonTypeConcat/{type}")
  public String getPokemonListConcat(@PathVariable String type) {
    return pokeService.getPokemonListConcat(type);
  }

  @GetMapping("/pokemonTypeCount/{type}")
  public String getPokemonListCount(@PathVariable String type) {
    return pokeService.getPokemonListCount(type);
  }

  @GetMapping("/pokemonByNivel/{nivel}")
    public List<Pokemon> getPokemonListPredicate(@PathVariable String nivel) {
        return pokeService.getPokemonListPredicate(nivel);
    }

   @GetMapping("/pokemonByExp/{increment}")
   public List<Pokemon> getPokemonListConsumer(@PathVariable int increment) {
       return pokeService.getPokemonListConsumer(increment);
   }

   @GetMapping("/pokemonAdd/{name}/{base_experience}")
   public List<Pokemon> getPokemonListSupplier(@PathVariable String name, @PathVariable int base_experience) {
       return pokeService.getPokemonListSupplier(name, base_experience);
   }


  @ExceptionHandler(PokemonNotFoundException.class)
  public ResponseEntity<ErrorResponse> handlePokemonNotFoundException(PokemonNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}