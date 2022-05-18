/*La capa de persistencia es la forma que tiene nuestro micro-servicio para guardar
 y recuperar las entidades que necesita. */
package com.prueba.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.usuario.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
