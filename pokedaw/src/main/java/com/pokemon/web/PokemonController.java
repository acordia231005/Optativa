package com.pokemon.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.services.PokemonServices;

@RestController
@RequestMapping("/pokedex")
public class PokemonController {

	@Autowired
	private PokemonServices pokemonServices;

}
