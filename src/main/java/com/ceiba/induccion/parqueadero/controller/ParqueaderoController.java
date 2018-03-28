package com.ceiba.induccion.parqueadero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ceiba.induccion.parqueadero.entity.CobroEntity;
import com.ceiba.induccion.parqueadero.model.Cobro;
import com.ceiba.induccion.parqueadero.model.CobroCarro;
import com.ceiba.induccion.parqueadero.model.CobroMoto;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.model.SolicitudServicio;
import com.ceiba.induccion.parqueadero.service.IParqueaderoService;

@RestController
public class ParqueaderoController {
	
	@Autowired
	IParqueaderoService parqueaderoService;
	
	@CrossOrigin
	@GetMapping("/")
	public String index() {
		return "<div style=\"text-align : center\">Bienvenidos al Parqueadero <h1>SEGURAR</div>";
	}
	
	@CrossOrigin
	@PostMapping("/disponibilidad")	
	public Servicio verificarDisponibilidadServicio(@RequestBody SolicitudServicio solicitudServicio) {
		return this.parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);		
	}
	
	@CrossOrigin
	@PostMapping("/entrada")
	public CobroEntity registrarEntradaCarro(@RequestBody Servicio servicio) {
		return this.parqueaderoService.registrarEntrada(servicio);		
	}	
	
	@CrossOrigin
	@GetMapping("/getAllCobros")
	public List<CobroEntity> consultarCobros(@RequestParam("estado") String estado) {
		return this.parqueaderoService.consultarCobros(estado);
	}
	
	@CrossOrigin
	@GetMapping("/salida")
	public Cobro registrarSalida(@RequestParam ("id") Long idCobro) {
		return this.parqueaderoService.registrarSalida(idCobro);
	}
}
