package com.ceiba.induccion.parqueadero.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParqueaderoController {
    
	@GetMapping("/")
	public String index() {
		return "|||||||||Bienvenidos al Parqueadero LOCAL SECURITY---|";
	}
}
