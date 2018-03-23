package com.ceiba.induccion.parqueadero.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ceiba.induccion.parqueadero.entity.ServicioEntity;
import com.ceiba.induccion.parqueadero.model.Servicio;

public final class ParqueaderoUtil {

	private static ParqueaderoUtil parqueaderoUtil;
	public static final String CUPO_NO_DISPONIBLE = "No existe cupo disponible en el momento";
	public static final String NO_ACCESO_PLACA_A = "Los dias Domingos y Lunes no se le permite acceso a las placas que empiezan por A";
	public static final String NO_ENCONTRADO_SERVICIO = "Servicio no encontrado";
	public static final String SERVICIO_PARQUEO_CARRO = "carro";
	public static final String SERVICIO_PARQUEO_MOTO = "moto";
	public static final String PLACA_EMPIEZA_CON_A = "A25-OP1";

	private ParqueaderoUtil() {

	}

	public static ParqueaderoUtil getInstance() {
		if (parqueaderoUtil == null) {
			parqueaderoUtil = new ParqueaderoUtil();
		}
		return parqueaderoUtil;

	}

	public Servicio convertirAServicioDTO(ServicioEntity servicioEntity) {
		Servicio servicio = null;
		if (servicioEntity != null) {
			servicio = new Servicio(servicioEntity);
		}
		return servicio;
	}

	public boolean esDomingoOLunes(Date fechaEvaluar) {
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(fechaEvaluar);
		return (fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
				|| fecha.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY);
	}

	public boolean esPlacaEmpiezaPorA(String placa) {
		return placa.toUpperCase().indexOf("A") == 0;
	}

	public static Date getFecha() {
		return new Date();
	}

	public static Date getFechaDiferenteDomingoYLunes() {		
		Date date = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			String dateInString = "22-03-2018 10:20:56";
			date = sdf.parse(dateInString);
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return date;
	}
	
	public static Date getFechaDomingoOLunes() {		
		Date date = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			String dateInString = "18-03-2018 10:20:56";
			date = sdf.parse(dateInString);
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return date;
	}
}