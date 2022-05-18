/*Entendemos como controlador a la capa de una aplicación que responde a eventos e invoca 
 *peticiones a la capa modelo. En nuestro ejemplo, los controladores están bajo el paquete controller*/

package com.prueba.usuario.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.usuario.dto.Mensaje;
import com.prueba.usuario.dto.UsuarioDto;
import com.prueba.usuario.model.Usuario;
import com.prueba.usuario.service.UsuarioService;


//indicamos que es un APIREST

@RestController
@RequestMapping("/usuario")
//Utilizamos el servicio desde el puerto de Angular.
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	//Retornamos Lista de usuarios
	
	@GetMapping("/lista")
	private ResponseEntity<List<Usuario>> getAllPersonas(){
		return ResponseEntity.ok(usuarioService.findAll());
	}
	
	//Busca el usuario con el ID
	
	@GetMapping("/detailId/{id}")
	private ResponseEntity<Usuario> findById(@PathVariable("id") Long id) {
		if(usuarioService.existsById(id)) 
			return ResponseEntity.ok(usuarioService.findById(id).get());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	//Busca el usuario con la Cedula
	
	@GetMapping("/detailCedula/{cedula}")
	private ResponseEntity<Usuario> findByCedula(@PathVariable("cedula") String cedula) {
		if(usuarioService.existsByCedula(cedula)) 
			return ResponseEntity.ok(usuarioService.findByCedula(cedula).get());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	//Crea un usuario, si sus argumentos son validos
	
	@PostMapping("/create")
	private ResponseEntity<?> save(@RequestBody UsuarioDto usuarioDto) {
		try {	
			if(StringUtils.isBlank(usuarioDto.getCedula()))
				return ResponseEntity.badRequest().body(new Mensaje("La cedula es obligatoria"));
			if(usuarioDto.getSalario() < 0) 
				return ResponseEntity.badRequest().body(new Mensaje("El salario debe ser mayor que 0"));
			if(usuarioService.existsByCedula(usuarioDto.getCedula())) 
				return ResponseEntity.badRequest().body(new Mensaje("El usuario ya existe"));
			//Obtenemos la fecha y hora actual y la pasamos en el constructotor de usuarios. 
			Date now = new Date();
			Usuario usuario = new Usuario(usuarioDto.getNombre(),usuarioDto.getApellido(),usuarioDto.getDni(),usuarioDto.getCedula(),usuarioDto.getDireccion(),now,usuarioDto.getSalario(),usuarioDto.getCargo());
			usuarioService.save(usuario);
			return ResponseEntity.ok().body(new Mensaje("Usuario se creó corretamente"));	
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	//Actualiza un usuario, si sus argumentos son validos
	
	@PutMapping("/update/{id}")
	private ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UsuarioDto usuarioDto) {
		try {
			if(!usuarioService.existsById(id))
				return ResponseEntity.badRequest().body(new Mensaje("Este usuario no existe"));
			if(StringUtils.isBlank(usuarioDto.getCedula()))
				return ResponseEntity.badRequest().body(new Mensaje("La cedula es obligatoria"));
			if(usuarioDto.getSalario() < 0) 
				return ResponseEntity.badRequest().body(new Mensaje("El salario debe ser mayor que 0"));
			if(usuarioService.existsByCedula(usuarioDto.getCedula()) && usuarioService.findByCedula(usuarioDto.getCedula()).get().getId_usuario() != id) 
				return ResponseEntity.badRequest().body(new Mensaje("Esta Cedula ya existe"));
			//Obteniene usuario
			Usuario  usuario = usuarioService.findById(id).get();
			//Seteamos los datos
			usuario.setNombre(usuarioDto.getNombre());
			usuario.setApellido(usuarioDto.getApellido());
			usuario.setCedula(usuarioDto.getCedula());
			usuario.setDireccion(usuarioDto.getDireccion());
			usuario.setSalario(usuarioDto.getSalario());
			usuario.setCargo(usuarioDto.getCargo());
			//Guarda Usuario
			usuarioService.save(usuario);
			return ResponseEntity.ok().body(new Mensaje("Usuario se actualizó corretamente"));	
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	//Elimina usuario segun el argumento ID que recibe.
	
	@DeleteMapping("/delete/{id}")
	private  ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if(!usuarioService.existsById(id))
			return ResponseEntity.badRequest().body(new Mensaje("El usuario no existe"));
		usuarioService.deleteById(id);
		return ResponseEntity.ok().body(new Mensaje("Usuario se ha eliminado."));	
	}
	
	
}
