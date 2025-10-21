package com.pokemon.services.Exception;

public class pokemonNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public pokemonNotFoundException(String message) {
		super(message);
	}
}
