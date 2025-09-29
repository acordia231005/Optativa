package com.daw.persistence.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.scheduling.config.Task;

import com.daw.persistence.entity.task;
import com.daw.persistence.entity.enums.Estado;

public interface taskRepository extends ListCrudRepository<task, Integer>{

	// Obtener el numero total de tareas completadas.
	long countByEstado(Estado estado);
	
	// Obtener una lista de las fechas de vencimiento de las tareas que esten en progreso.
	List<Task> findByEstado(Estado estado);
	
	// Obtener las tareas vencidas.
	List<Task> findByFechaVencimientoBefore(LocalDate fecha);
	
	//Obtener las tareas no vencidas.
	List<Task> findByFechaVencimientoAfter(LocalDate fecha);
	
	// Ordenar tareas por fecha de vencimiento.
	List<Task> findAllByOrderByFechaVencimiento();
	
	// Obtener tareas por titulo
	List<Task> findByTituloContainingIgnoreCase(String titulo);
}
