package com.ceiba.induccion.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ceiba.induccion.parqueadero.entity.CobroEntity;

public interface CobroRepository extends JpaRepository<CobroEntity, Long> {

}
