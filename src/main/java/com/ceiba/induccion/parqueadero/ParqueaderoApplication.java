package com.ceiba.induccion.parqueadero;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ceiba.induccion.parqueadero.entity.ServicioEntity;
import com.ceiba.induccion.parqueadero.repository.ServicioRepository;

@SpringBootApplication
public class ParqueaderoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initServiciosParqueadero(ServicioRepository servicioRepository) {
		return args -> {
			ServicioEntity servicioCarro = servicioRepository.findByDescripcion("carro");
			ServicioEntity servicioMoto = servicioRepository.findByDescripcion("moto");
			if(servicioCarro == null) {
				servicioCarro = new ServicioEntity("carro", 20, 20, 1000, 8000);
				servicioRepository.save(servicioCarro);
			}
			
			if(servicioMoto == null) {
				servicioMoto = new ServicioEntity("moto", 10, 10, 500, 4000);
				servicioRepository.save(servicioMoto);
			}
		};

	}
}
