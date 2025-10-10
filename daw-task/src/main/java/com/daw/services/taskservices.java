package com.daw.services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entity.task;
import com.daw.persistence.entity.enums.Estado;
import com.daw.persistence.repository.taskRepository;
import com.daw.services.exceptions.TaskException;
import com.daw.services.exceptions.TaskNotFoundException;

@Service
public class taskservices {

	@Autowired
	private taskRepository taskRepository;
	
	public List<task> findAll(){    
		return this.taskRepository.findAll();
	}	
	public task findById(int idTarea){
		if (!this.taskRepository.existsById(idTarea)) {
			throw new TaskNotFoundException("No existe la tarea con id: " + idTarea);
		}		
		return this.taskRepository.findById(idTarea).get();
	}
	
	public boolean existsById(int idTarea) {
		return this.taskRepository.existsById(idTarea);
	}
	
	public void deleteById(int idTarea) {
		
		
		if (this.taskRepository.existsById(idTarea)) {
				this.taskRepository.deleteById(idTarea);
		}
		return;
		
	}
	public task create(task tarea) {
		if (tarea.getFechaVencimiento().isBefore(LocalDate.now())) {
			throw new TaskException("La fecha no es correcata");
		}
		
		tarea.setId(0);
		tarea.setEstado(Estado.PENDIENTE);
		tarea.setFechaCreacion(LocalDate.now());
		
		return this.taskRepository.save(tarea);
	}
//	public int update(task tarea, int idTarea) {
//		if (tarea.getId() != idTarea) {
//			throw new TaskException("La id del body y la pasada no coinciden");
//		}
//		if (this.taskRepository.existsById(idTarea)) {
//			throw new TaskNotFoundException("La Tarea no existe");
//		}
//		if (tarea.getEstado() != null) {
//			throw new TaskException(null);
//		}
//		if (tarea.getFechaCreacion() != null) {
//			throw new TaskException(null);
//		}
//		
//		return idTarea;
//		
//	}
}