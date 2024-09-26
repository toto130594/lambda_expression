package com.nttdata.prueba.util;

import com.nttdata.prueba.model.Pokemon;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TestUtil {
    public List<Pokemon> buildPokemonList() {
      List<Pokemon> pokemonList = new ArrayList<>();

      pokemonList.add(new Pokemon(1, 1, "Pikachu", 112, 4, 60, null, null, null, null));
      pokemonList.add(new Pokemon(2, 2, "Charmander", 62, 6, 85, null, new ArrayList<>(), null, null));
      pokemonList.add(new Pokemon(3, 3, "Bulbasaur", 64, 7, 69, null, null, null, null));
      pokemonList.add(new Pokemon(4, 4, "Squirtle", 63, 5, 90, null, null, null, null));
      pokemonList.add(new Pokemon(5, 5, "Jigglypuff", 50, 5, 55, null, null, null, null));
      pokemonList.add(new Pokemon(6, 6, "Meowth", 58, 4, 42, null, null, null, null));
      pokemonList.add(new Pokemon(7, 7, "Psyduck", 64, 8, 80, null, null, null, null));
      pokemonList.add(new Pokemon(8, 8, "Machop", 61, 8, 70, null, null, null, null));
      pokemonList.add(new Pokemon(9, 9, "Geodude", 60, 4, 200, null, null, null, null));
      pokemonList.add(new Pokemon(10, 10, "Ponyta", 82, 10, 300, null, new ArrayList<>(), null, null));

      return pokemonList;
    }
}
