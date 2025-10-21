package com.pokemon.persistence.Entity;

import java.time.LocalDate;

import com.pokemon.persistence.Entity.Enums.Pokeball;
import com.pokemon.persistence.Entity.Enums.Tipo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "Pokemon")
@Getter
@Setter
public class Pokemon {
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private int id;
			
			@Column(name = "numero_pokedex")
			private int numeroPokedex;
			
			private String nombre;
			
			@Enumerated(EnumType.STRING)
			private Tipo tipo1;
			
			@Enumerated(EnumType.STRING)
			private Tipo tipo2;
			
			@Column(name = "fecha_captura")
			private LocalDate fechaCaptura;
			
			@Enumerated(EnumType.STRING)
			private Pokeball capturado;
			
}
