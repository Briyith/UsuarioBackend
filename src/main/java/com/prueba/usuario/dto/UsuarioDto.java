/*El patrón DTO tiene como finalidad de crear un objeto plano (POJO) con una serie de 
 * atributos que puedan ser enviados o recuperados del servidor en una sola invocación*/

package com.prueba.usuario.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prueba.usuario.model.Cargo;

public class UsuarioDto {
	
	private String nombre;
	private String apellido;
	private String dni;
	@NotBlank
	private String cedula;
	private String direccion;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date fecha_creacion;
	@Min(0)
	private double salario;
	private Cargo cargo;
	
	public UsuarioDto() {}

	

	public UsuarioDto(String nombre, String apellido, String dni, @NotBlank String cedula, String direccion,
			Date fecha_creacion, @Min(0) double salario, Cargo cargo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.cedula = cedula;
		this.direccion = direccion;
		this.fecha_creacion = fecha_creacion;
		this.salario = salario;
		this.cargo = cargo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public String getCedula() {
		return cedula;
	}



	public void setCedula(String cedula) {
		this.cedula = cedula;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public Date getFecha_creacion() {
		return fecha_creacion;
	}



	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}



	public double getSalario() {
		return salario;
	}



	public void setSalario(double salario) {
		this.salario = salario;
	}



	public Cargo getCargo() {
		return cargo;
	}



	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}





}
