package com.ceiba.induccion.parqueadero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.ceiba.induccion.parqueadero.entity.CobroEntity;

public interface CobroRepository extends JpaRepository<CobroEntity, Long> {
	
	public  CobroEntity findByPlaca(String placa);
	
	public  CobroEntity findById(long id);
	
	public List<CobroEntity> findAllByEstado(String estado);
	
	@Modifying
	@Query("update CobroEntity c set c.estado = ?1 where c.id = ?2")
	int actualizarEstadoCobro(String estado, long id);

}
