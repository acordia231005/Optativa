package com.pokemon.persistence.Repository;

import org.springframework.data.repository.ListCrudRepository;

import com.pokemon.persistence.Entity.Pokemon;

public interface PokemonRepository extends ListCrudRepository<Pokemon, Integer>{

}
