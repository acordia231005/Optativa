package daw.Pokemon.persistence.Repository;

import org.springframework.data.repository.ListCrudRepository;

import daw.Pokemon.persistence.Entity.Pokemon;

public interface PokemonRepository extends ListCrudRepository<Pokemon, Integer>{

}
