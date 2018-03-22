package com.ceiba.induccion.parqueadero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ceiba.induccion.parqueadero.model.Cobro;
import com.ceiba.induccion.parqueadero.model.CobroCarro;
import com.ceiba.induccion.parqueadero.model.CobroMoto;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.service.IParqueaderoService;

@RestController
@SessionAttributes("cobro")
public class ParqueaderoController {
	
	@Autowired
	IParqueaderoService parqueaderoService;
	
	@ModelAttribute("cobro")
	public Cobro getCobroServicio() { 
	   return new CobroCarro();
	}
    
	@GetMapping("/")
	public String index() {
		return "<div style=\"text-align : center\">Bienvenidos al Parqueadero <h1>SEGURAR</div>";
	}
	
	@GetMapping("/disponibilidad")	
	public Servicio verificarDisponibilidadServicio(@RequestParam ("placa") String placa,@RequestParam ("tipoServicio") String tipoServicio) {
		return this.parqueaderoService.verificarDisponibilidadServicio(placa,tipoServicio);		
	}
	
	@PostMapping("/entradaCarro")
	public Cobro registrarEntradaCarro(@RequestBody CobroCarro cobro) {
		return null;
	}
	
	@PostMapping("/entradaMoto")
	public Cobro registrarEntradaMoto(@RequestBody CobroMoto cobro) {
		return null;
	}
	
	
	
	public Cobro registrarSalida() {
		return null;
	}
}
