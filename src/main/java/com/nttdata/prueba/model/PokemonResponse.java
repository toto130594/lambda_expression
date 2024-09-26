package com.nttdata.prueba.model;

import java.util.List;
import lombok.Data;

@Data
public class PokemonResponse {
  private List<Pokemon> results;
}
