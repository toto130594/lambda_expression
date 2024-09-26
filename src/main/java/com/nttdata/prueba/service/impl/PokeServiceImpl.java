package com.nttdata.prueba.service.impl;

import com.nttdata.prueba.exception.PokemonNotFoundException;
import com.nttdata.prueba.model.Pokemon;
import com.nttdata.prueba.model.PokemonResponse;
import com.nttdata.prueba.service.BattleInterface;
import com.nttdata.prueba.service.PokeService;
import com.nttdata.prueba.util.TestUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeServiceImpl implements PokeService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private TestUtil testUtil;

  @Override
  public Pokemon getPokemon(String name) {
    String url = "https://pokeapi.co/api/v2/pokemon/" + name;
    try {
      return restTemplate.getForObject(url, Pokemon.class);
    } catch (HttpClientErrorException.NotFound e) {
      throw new PokemonNotFoundException("Pokemon not found: " + name);
    }
  }

  @Override
  public List<Pokemon> getPokemonList() {
    String url = "https://pokeapi.co/api/v2/pokemon?limit=150";
    PokemonResponse response = restTemplate.getForObject(url, PokemonResponse.class);
    if (response == null || response.getResults() == null) {
      throw new PokemonNotFoundException("No se encontraron Pokémon.");
    }
    return response.getResults().parallelStream()
        .map(pokemon -> getPokemon(pokemon.getName()))
        .toList();
  }

  @Override
  public String battlePokemon(String pokemon1, String pokemon2) {
    BattleInterface battle = (p1, p2) -> {
      Pokemon poke1 = getPokemon(p1);
      Pokemon poke2 = getPokemon(p2);
      int exp1 = poke1.getBase_experience();
      int exp2 = poke2.getBase_experience();

      if (exp1 == exp2){
        return " Es un empate!";
      }
      return (exp1 > exp2 ? poke1 : poke2).getName() +" es el ganador!";

    };
    return battle.fight(pokemon1, pokemon2);
  }

  @Override
  public List<String> getPokemonListType(String type) {
      List<Pokemon> listPokemon = new ArrayList<>(getPokemonList());
      listPokemon.addAll(testUtil.buildPokemonList());

      return listPokemon.stream()
          .filter(pokemon -> pokemon.getTypes() != null)
          .filter(pokemon -> pokemon.getTypes().stream()
              .anyMatch(pokemonType -> pokemonType.getType().getName().equals(type)))
          .map(pokemon -> pokemon.getName().toUpperCase())
          .sorted()
          .distinct()
          .toList();
  }

  @Override
  public String getPokemonListConcat(String type) {
    List<Pokemon> listPokemon = new ArrayList<>(getPokemonList());
    return listPokemon.stream()
        .filter(pokemon -> pokemon.getTypes().stream()
            .anyMatch(t -> "all".equals(type) || t.getType().getName().equals(type) ))
        .map(Pokemon::getName)
        .reduce("", (name1 , name2) -> name1 + " , " + name2);
  }

  @Override
  public String getPokemonListCount(String type) {
    List<Pokemon> listPokemon = new ArrayList<>(getPokemonList());
    long count = listPokemon.stream()
        .filter(pokemon -> pokemon.getTypes().stream()
            .anyMatch(t -> "all".equals(type) || t.getType().getName().equals(type)))
        .count();

    return "Cantidad de pokemones de tipo " + type + " : " + count;
  }

  @Override
  public List<Pokemon> getPokemonListPredicate(String nivel) { //high
    List<Pokemon> listPokemon = new ArrayList<>(getPokemonList());
    Map<String, IntPredicate> levelPredicates = Map.of(
      "low", exp -> exp < 100,
        "medium", exp -> exp >= 100 && exp < 200,
        "high", exp -> exp >= 200
    );

    IntPredicate levelPredicate = levelPredicates.getOrDefault(nivel, exp -> false);

    return listPokemon.stream()
        .filter(pokemon -> levelPredicate.test(pokemon.getBase_experience()))
        .toList();
  }

  @Override
  public List<Pokemon> getPokemonListConsumer(int increment) {
    List<Pokemon> listPokemon = new ArrayList<>(getPokemonList());
    Consumer<Pokemon> expConsumer = pokemon -> pokemon.setBase_experience(pokemon.getBase_experience() + increment);
    listPokemon.forEach(expConsumer);
    return listPokemon;
  }

  @Override
  public List<Pokemon> getPokemonListSupplier(String name, int base_experience) {
    List<Pokemon> listPokemon = new ArrayList<>(getPokemonList());
    Supplier<Pokemon> newPokemonSupplier = () -> {
      return new Pokemon(151, base_experience, name, 100, 4, 40, null, null, null, null);
    };

    listPokemon.add(newPokemonSupplier.get());

    return listPokemon;
  }


}