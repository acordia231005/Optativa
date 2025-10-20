package com.daw.web;

import com.daw.persistence.entity.Tarea;
import com.daw.services.taskservices;
import com.daw.services.exceptions.TaskException;
import com.daw.services.exceptions.TaskNotFoundException;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
@RequestMapping("/tareas")
public class TaskController {
	
	@Autowired
	private taskservices tareaService;
	
	@GetMapping
	public ResponseEntity<?> list(){
		return ResponseEntity.ok(this.tareaService.findAll());
	}
	
	@GetMapping("/{idTarea}")
	public ResponseEntity<?> findById(@PathVariable int idTarea){
		try {
			return ResponseEntity.ok(this.tareaService.findById(idTarea));
		} catch (TaskNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	public boolean existsById(int idtarea) {
		return this.tareaService.existsById(idtarea);
	}
	@DeleteMapping("/{idTarea}")
	public ResponseEntity<?> delete(@PathVariable int idTarea) {
		try {
			this.tareaService.deleteById(idTarea);
			return ResponseEntity.ok().build();
		}
		catch (TaskNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Tarea tarea){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.tareaService.create(tarea));
		} catch (TaskException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	@PutMapping("/{idTarea}")
	public ResponseEntity<?> update(@PathVariable int idTarea, @RequestBody Tarea tarea){
		try {
			return ResponseEntity.ok().build();
		} catch (TaskNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	@PutMapping("/{idTarea}/iniciar")
	public ResponseEntity<?> iniciarTarea(@PathVariable int idTarea){
		try {
			return ResponseEntity.ok(this.tareaService.iniciarTarea(idTarea));
		}catch(TaskNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch (TaskException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{idTarea}/completar")
	public ResponseEntity<?> completarTarea(@PathVariable int idTarea){
		try {
			return ResponseEntity.ok(this.tareaService.completarTarea(idTarea));
		}catch(TaskNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch (TaskException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
	
	@GetMapping("/tareasPendientes")
	public ResponseEntity<?> obtenerTareasPendientes(){
		try {
			return ResponseEntity.ok(this.tareaService.obtenerTareasPendientes());
		}catch (TaskException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
	
	@GetMapping("/tareasEnProgreso")
	public ResponseEntity<?> obtenerTareasEnProgreso(){
		try {
			return ResponseEntity.ok(this.tareaService.obtenerTareasEnProgreso());
		}catch (TaskException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
	
	@GetMapping("/tareasCompletadas")
	public ResponseEntity<?> obtenerTareasCompletada(){
		try {
			return ResponseEntity.ok(this.tareaService.obtenerTareasCompletada());
		}catch (TaskException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
	
	@GetMapping("/tareasVencidas")
	public ResponseEntity<?> obtenerTareasVencidas(){
		try {
			return ResponseEntity.ok(this.tareaService.obtenerTareasVencidas());
		}catch(TaskNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch (TaskException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
		
	}
	
	@GetMapping("/tareasNoVencidas")
	public ResponseEntity<?> obtenerTareasNoVencidas(){
		try {
			return ResponseEntity.ok(this.tareaService.obtenerTareasNoVencidas());
		}catch(TaskNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch (TaskException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
	
	@GetMapping("/buscarTitulo")
	public ResponseEntity<?> buscarTitulo(@RequestParam String titulo) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.tareaService.buscarPorTitulo(titulo));
		}catch(TaskNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch (TaskException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
}