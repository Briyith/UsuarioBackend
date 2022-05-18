/*La capa de persistencia es la forma que tiene nuestro micro-servicio para guardar
 y recuperar las entidades que necesita. */

package com.prueba.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.usuario.model.Usuario;

//extends JpaRepository<Usuario,Long>, implementa los metodos principales y esta interfaz necesita ser definida.
@Repository
public interface UsuarioRpository extends JpaRepository<Usuario,Long> {
	Optional<Usuario> findByCedula(String cedula);
	boolean existsByCedula(String cedula);
}
