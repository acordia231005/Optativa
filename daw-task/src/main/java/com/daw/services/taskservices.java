package com.daw.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
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
	
	public task findByIdFuncional(int idTarea){
		return this.taskRepository.findById(idTarea)
				.orElseThrow(() -> 
				new TaskNotFoundException("No existe la tarea con id: " + idTarea)
				);
	}
	
	public boolean existsById(int idTarea) {
		return this.taskRepository.existsById(idTarea);
	}
	
	public task create() {
		
	}
	
	public void deleteById(int idTarea) {
		boolean result = false;
		
		if (this.taskRepository.existsById(idTarea)) {
				this.taskRepository.deleteById(idTarea);
				result = true;
		}
		return;
		
	}
	
	public boolean deleteDeclarativo(int idtarea) {
		boolean result = true;
		
		if (this.taskRepository.existsById(idtarea)) {
			this.taskRepository.deleteById(idtarea);
			result = true;
		}
		return result;
	}
	public boolean deleteFuncional(int idtarea) {
		return this.taskRepository.findById(idtarea)
				.map(t ->{
					this.taskRepository.deleteById(idtarea);
					return true;
				})
				.orElse(false);
		}
	

	
}