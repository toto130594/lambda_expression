package com.nttdata.prueba.service;


import com.nttdata.prueba.model.Pokemon;
import java.util.List;

public interface PokeService {
   Pokemon getPokemon(String name);
    List<Pokemon> getPokemonList();
    String battlePokemon(String pokemon1, String pokemon2);
    List<String> getPokemonListType(String type);
    String getPokemonListConcat(String type);
    String getPokemonListCount(String type);
    List<Pokemon> getPokemonListPredicate(String nivel);
    List<Pokemon> getPokemonListConsumer(int increment);
    List<Pokemon> getPokemonListSupplier(String name, int base_experience);
}
