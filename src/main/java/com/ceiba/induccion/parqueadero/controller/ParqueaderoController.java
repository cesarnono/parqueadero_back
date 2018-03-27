package com.ceiba.induccion.parqueadero.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@GetMapping("/")
	public String index() {
		return "<div style=\"text-align : center\">Bienvenidos al Parqueadero <h1>SEGURAR</div>";
	}
	
	@PostMapping("/disponibilidad")	
	public Servicio verificarDisponibilidadServicio(@RequestBody SolicitudServicio solicitudServicio) {
		return this.parqueaderoService.verificarDisponibilidadServicio(solicitudServicio);		
	}
	
	@PostMapping("/entrada")
	public CobroEntity registrarEntradaCarro(@RequestBody Servicio servicio) {
		return this.parqueaderoService.registrarEntrada(servicio);		
	}	
	
	
	@GetMapping("/getAllCobros")
	public Cobro consultarCobro() {
		return null;
	}
	
	
	@GetMapping("/salida")
	public Cobro registrarSalida(@RequestParam ("id") Long idCobro) {
		return this.parqueaderoService.registrarSalida(idCobro);
	}
}
