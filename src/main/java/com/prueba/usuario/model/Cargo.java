/*capa modelo o de negocio de la aplicación,
 * comenzaremos por la capa más baja de acceso a datos y manipulación, para la cual nos 
 * ayudaremos de distintas tecnologías, librerías y proyectos. */

package com.prueba.usuario.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="cargo")
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_cargo;
	private String nombre;
	
	
	
	
	public Cargo() {}




	public Cargo(String nombre) {
		super();
		this.nombre = nombre;
	}



	public int getId_cargo() {
		return id_cargo;
	}




	public void setId_cargo(int id_cargo) {
		this.id_cargo = id_cargo;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



}