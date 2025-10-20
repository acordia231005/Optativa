package com.daw.services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entity.Tarea;
import com.daw.persistence.entity.enums.Estado;
import com.daw.persistence.repository.TareaRepository;
import com.daw.services.exceptions.TaskException;
import com.daw.services.exceptions.TaskNotFoundException;

@Service
public class taskservices {

	@Autowired
	private TareaRepository taskRepository;
	
	public List<Tarea> findAll(){    
		return this.taskRepository.findAll();
	}	
	public Tarea findById(int idTarea){
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
	public Tarea create(Tarea tarea) {
		if (tarea.getFechaVencimiento().isBefore(LocalDate.now())) {
			throw new TaskException("La fecha no es correcata");
		}
		
		tarea.setId(0);
		tarea.setEstado(Estado.PENDIENTE);
		tarea.setFechaCreacion(LocalDate.now());
		
		return this.taskRepository.save(tarea);
	}
	public Tarea update(Tarea tarea, int idTarea) {
		if (tarea.getId() != idTarea) {
			throw new TaskException("La id del body y la pasada no coinciden");
		}
		if (this.taskRepository.existsById(idTarea)) {
			throw new TaskNotFoundException("La Tarea no existe");
		}
		if (tarea.getEstado() != null) {
			throw new TaskException(null);
		}
		if (tarea.getFechaCreacion() != null) {
			throw new TaskException(null);
		}
		
		Tarea tareaBD = this.findById(idTarea);
		tarea.setTitulo(tareaBD.getTitulo());
		tarea.setDescripcion(tareaBD.getDescripcion());
		tarea.setFechaVencimiento(tareaBD.getFechaVencimiento());
		return this.taskRepository.save(tareaBD);
	}
	public Tarea iniciarTarea(int idTarea) {
		if (!this.existsById(idTarea)) {
			throw new TaskNotFoundException("No se ha encontrado la tarea con ID: " + idTarea);
			}
		if (this.findById(idTarea).getEstado() != Estado.PENDIENTE) {
			new TaskNotFoundException("No existe la tarea con id: " + idTarea);
		}
		Tarea tarea = this.findById(idTarea);
		tarea.setEstado(Estado.EN_PROGRESO);
		
		return this.taskRepository.save(tarea);
	}
	public Tarea completarTarea(int idTarea) {
		if (!this.existsById(idTarea)) {
			throw new TaskNotFoundException("No se ha encontrado la tarea con ID: " + idTarea);
			}
		if (this.findById(idTarea).getEstado() != Estado.EN_PROGRESO) {
			new TaskNotFoundException("No existe la tarea con id: " + idTarea);
		}
		Tarea tarea = this.findById(idTarea);
		tarea.setEstado(Estado.COMPLETADA);
		
		return this.taskRepository.save(tarea);
	}
	
	public List<Tarea> obtenerTareasPendientes(){
		return this.taskRepository.findByEstado(Estado.PENDIENTE);
	}
	
	public List<Tarea> obtenerTareasEnProgreso(){
		return this.taskRepository.findByEstado(Estado.EN_PROGRESO);
	}
	
	public List<Tarea> obtenerTareasCompletada(){
		return this.taskRepository.findByEstado(Estado.COMPLETADA);
	}
	public List<Tarea> obtenerTareasVencidas(){
		return this.taskRepository.findByFechaVencimientoBefore(LocalDate.now());
	}
	
	public List<Tarea> obtenerTareasNoVencidas(){
		return this.taskRepository.findByFechaVencimientoAfter(LocalDate.now());
	}
	public List<Tarea> buscarPorTitulo(String titulo){
		return this.taskRepository.findByTituloContainingIgnoreCase(titulo);
	}
}