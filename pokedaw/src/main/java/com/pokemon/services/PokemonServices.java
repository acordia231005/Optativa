package com.pokemon.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemon.persistence.Entity.Pokemon;
import com.pokemon.persistence.Entity.Enums.Pokeball;
import com.pokemon.persistence.Entity.Enums.Tipo;
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

	public Pokemon create(Pokemon pokemon) {
		if (pokemon.getTipo1() == pokemon.getTipo2()) {
			throw new pokemonException("Los tipos no pueden ser iguales");
		}
		if (pokemon.getCapturado() == null) {
			pokemon.setCapturado(Pokeball.POKEBALL);
		}
		if (pokemon.getTipo2() == null) {
			pokemon.setTipo2(Tipo.NINGUNO);
		}
		
		pokemon.setId(0);
		
		return this.pokemonRepository.save(pokemon);
	}
	
	public Pokemon update(Pokemon pokemon, int id) {
		if (pokemon.getId() != id) {
			throw new pokemonException("La id del body y la pasada no coinciden");
		}
		if (this.pokemonRepository.existsById(id)) {
			throw new pokemonNotFoundException("La Tarea no existe");
		}
		if (pokemon.getCapturado() != null) {
			throw new pokemonException(null);
		}
		if (pokemon.getFechaCaptura() != null) {
			throw new pokemonException(null);
		}
		
		Pokemon pokemonBD = this.findById(id);
		pokemon.setNumeroPokedex(pokemon.getNumeroPokedex());
		pokemon.setNombre(pokemon.getNombre());
		return this.pokemonRepository.save(pokemonBD);
	}
	
	public void delete(int id) {
		if (this.pokemonRepository.existsById(id)) {
			this.pokemonRepository.deleteById(id);
		}
		return;
	}
	
	public List<Pokemon> findByNumPokedex(int numero) {
		return this.pokemonRepository.findByNumeroPokedex(numero);
	}
	
	public List<Pokemon> FindByRangoFechas(LocalDate start, LocalDate end) {
		return this.pokemonRepository.findByCapturadoByFechaCapturaBetween(start, end);
	}
	
	public List<Pokemon> FindByTipo(String tipo) {
		return this.pokemonRepository.findByTipo1OrTipo2(tipo);
	}
	
	public Pokemon cambiarTipo(int id, String tipo1, String tipo2) {	
		try {
			Tipo tip0 = Tipo.valueOf(tipo1);
			Tipo tipo = Tipo.valueOf(tipo2);
		} catch (pokemonException ex) {
			return 
// 
		}
		Pokemon pokemon = this.findById(id);
		pokemon.setTipo1(tipo1);
		pokemon.setTipo2(tipo2);
		
		return this.pokemonRepository.save(pokemon);
	}
}