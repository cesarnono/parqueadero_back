package com.ceiba.induccion.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceiba.induccion.parqueadero.entity.ServicioEntity;

public interface ServicioRepository extends JpaRepository<ServicioEntity, Long>{

}
