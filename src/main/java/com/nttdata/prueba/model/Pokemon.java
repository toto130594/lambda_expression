package com.nttdata.prueba.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pokemon {
  private int id;
  private int base_experience;
  private String name;
  private int height;
  private int weight;
  private int order;
  private List<Ability> abilities;
  private List<Type> types;
  private Sprites sprites;
  private EvolutionChain evolution_chain;

  @Data
  public static class Sprites {
    private String front_default;
  }

  @Data
  public static class Ability {
    private AbilityDetail ability;

    @Data
    public static class AbilityDetail {
      private String name;
    }
  }

  @Data
  public static class Type {
    private TypeDetail type;

    @Data
    public static class TypeDetail {
      private String name;
    }
  }

  @Data
  public static class EvolutionChain {
    private String url;
  }

}