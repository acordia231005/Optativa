package com.daw.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import com.daw.persistence.entity.Tarea;
import com.daw.persistence.entity.enums.Estado;

public interface TareaRepository extends ListCrudRepository<Tarea, Integer>{


	// Obtener una lista de las fechas de vencimiento de las tareas que esten en progreso.
	List<Tarea> findByEstado(Estado estado);
	
	// Obtener las tareas vencidas.
	List<Tarea> findByFechaVencimientoBefore(LocalDate fecha);
	
	//Obtener las tareas no vencidas.
	List<Tarea> findByFechaVencimientoAfter(LocalDate fecha);
	
	// Obtener tareas por titulo
	List<Tarea> findByTituloContainingIgnoreCase(String titulo);
	
}