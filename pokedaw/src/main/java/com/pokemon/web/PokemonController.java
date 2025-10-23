package com.pokemon.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.persistence.Entity.Pokemon;
import com.pokemon.services.PokemonServices;
import com.pokemon.services.Exception.pokemonException;
import com.pokemon.services.Exception.pokemonNotFoundException;

@RestController
@RequestMapping("/pokedex")
public class PokemonController {

	@Autowired
	private PokemonServices pokemonServices;

	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(this.pokemonServices.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> FindById(@PathVariable int id){
		try {
			return ResponseEntity.ok(this.pokemonServices.findById(id));
		} catch (pokemonNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pokemon pokemon){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pokemonServices.create(pokemon));
		} catch (pokemonException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{idTarea}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody Pokemon pokemon){
		try {
			return ResponseEntity.ok().build();
		} catch (pokemonNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			this.pokemonServices.delete(id);
			return ResponseEntity.ok().build();
		}
		catch (pokemonNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
}
