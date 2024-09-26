package com.nttdata.prueba.exception;

public class PokemonNotFoundException  extends RuntimeException{
  public PokemonNotFoundException(String message) {
    super(message);
  }
}
