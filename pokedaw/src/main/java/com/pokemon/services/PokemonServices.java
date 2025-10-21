package com.pokemon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemon.persistence.Entity.Pokemon;
import com.pokemon.persistence.Entity.Enums.Pokeball;
import com.pokemon.persistence.Repository.PokemonRepository;
import com.pokemon.services.Exception.pokemonException;
import com.pokemon.services.Exception.pokemonNotFoundException;

@Service
public class PokemonServices {

	@Autowired
	private PokemonRepository pokemonRepository;
	
	public List<Pokemon> findAll(){
		return this.pokemonRepository.findAll();
	}
	
	public Pokemon findById(int id){
		if (!this.pokemonRepository.existsById(id)) {
			throw new pokemonNotFoundException("No existe la tarea con id: " + id);
		}		
		return this.pokemonRepository.findById(id).get();
	}
	
	public boolean existsById(int id) {
		return this.pokemonRepository.existsById(id);
	}
	
	public void deleteById(int id) {
		if (this.pokemonRepository.existsById(id)) {
			this.pokemonRepository.deleteById(id);
		}
		return;
	}
	
	public Pokemon create(Pokemon pokemon) {
		if (pokemon.getTipo1() == pokemon.getTipo2()) {
			throw new pokemonException("Los tipos no pueden ser iguales");
		}
		
		pokemon.setId(0);
		pokemon.setCapturado(Pokeball.POKEBALL);
		
		return this.pokemonRepository.save(pokemon);
	}
}
