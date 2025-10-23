package com.pokemon.persistence.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.pokemon.persistence.Entity.Pokemon;

public interface PokemonRepository extends ListCrudRepository<Pokemon, Integer>{

	
	// Buscar un pokemon según su número de la pokédex.
	List<Pokemon> findByNumeroPokedex(int numeroPokedex);
	
	// Buscar los pokemon capturados en un rango de fechas.
	List<Pokemon> findByCapturadoByFechaCapturaBetween(LocalDate start, LocalDate end)	;
	
	// Buscar pokemons de un tipo determinado (da igual que sea tipo1 o tipo2).
	List<Pokemon> findByTipo1OrTipo2(String tipo);
	
}
