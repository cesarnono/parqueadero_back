package com.ceiba.induccion.parqueadero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ceiba.induccion.parqueadero.entity.ServicioEntity;

public interface ServicioRepository extends JpaRepository<ServicioEntity, Long>{
	
	public ServicioEntity findByDescripcion(String descripcion);
	
	@Modifying
	@Query("update ServicioEntity s set s.cupoDisponible = (s.cupoDisponible-1) where s.id = ?1")
	int descontarCupoDisponible(long id);
	
	@Modifying
	@Query("update ServicioEntity s set s.cupoDisponible = (s.cupoDisponible+1) where s.id = ?1 ")
	int aumentarCupoDisponible(long id);
}
