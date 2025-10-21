package com.pokemon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemon.persistence.Repository.PokemonRepository;

@Service
public class PokemonServices {

	@Autowired
	private PokemonRepository pokemonRepository;
	
	
	
}
