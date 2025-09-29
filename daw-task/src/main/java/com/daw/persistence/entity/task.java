package com.daw.persistence.entity;

import java.time.LocalDate;

import com.daw.persistence.entity.enums.Estado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "task")
public class task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String descripcion;
	
	@Column(name = "fecha_creacion")
	private LocalDate fechaCreacion;
	
	@Column(name = "fecha_vencimiento")
	private LocalDate fechaVencimiento;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	
}
