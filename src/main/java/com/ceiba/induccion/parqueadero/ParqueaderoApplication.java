package com.ceiba.induccion.parqueadero;

import java.util.Calendar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ceiba.induccion.parqueadero.entity.CobroEntity;
import com.ceiba.induccion.parqueadero.entity.ServicioEntity;
import com.ceiba.induccion.parqueadero.model.CobroCarro;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.repository.CobroRepository;
import com.ceiba.induccion.parqueadero.repository.ServicioRepository;
import com.ceiba.induccion.parqueadero.util.CobroBuilder;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;

@SpringBootApplication
public class ParqueaderoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initServiciosParqueadero(ServicioRepository servicioRepository,CobroRepository cobroRepository) {
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
			
		   /*servicioCarro = servicioRepository.findByDescripcion(ParqueaderoUtil.SERVICIO_PARQUEO_CARRO);
		   if(servicioCarro != null) {			   
			   CobroCarro cobroCarro = new CobroBuilder()
						.withFechaEntrada(ParqueaderoUtil.restarHorasCalendar(Calendar.getInstance(), -5))
						.withFechaSalida(null).whithEstado("PENDIENTE").withServicio(new Servicio(servicioCarro))
						.buildCarro();
				CobroEntity cobroEntity = new CobroEntity(cobroCarro);
				cobroRepository.save(cobroEntity);
				servicioRepository.descontarCupoDisponible(servicioCarro.getId());
		   }*/
			
		};

	}
}
