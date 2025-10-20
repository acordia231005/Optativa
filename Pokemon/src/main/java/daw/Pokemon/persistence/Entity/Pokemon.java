package daw.Pokemon.persistence.Entity;

import java.time.LocalDate;

import daw.Pokemon.persistence.Entity.Enums.Capturado;
import daw.Pokemon.persistence.Entity.Enums.Tipo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

public class Pokemon {
@Entity
@Getter
@Setter
@Table(name = "tarea")
public class Tarea {

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
		private Capturado capturado;
		
	}
}