package com.ceiba.induccion.parqueadero.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ceiba.induccion.parqueadero.entity.ServicioEntity;
import com.ceiba.induccion.parqueadero.model.Servicio;
import com.ceiba.induccion.parqueadero.model.TiempoServicio;

public final class ParqueaderoUtil {

	private static ParqueaderoUtil parqueaderoUtil;
	public static final String CUPO_NO_DISPONIBLE = "No existe cupo disponible en el momento";
	public static final String NO_ACCESO_PLACA_A = "Los dias Domingos y Lunes no se le permite acceso a las placas que empiezan por A";
	public static final String SERVICIO_PARQUEO_CARRO = "carro";
	public static final String SERVICIO_PARQUEO_MOTO = "moto";
	public static final String PLACA_EMPIEZA_CON_A = "A25-OP1";
	public static final String COBRO_PENDIENTE = "PENDIENTE";
	public static final String COBRO_FINALIZADO = "FINALIZADO";
	public static final String ERROR_REGISTRAR_ENTRADA = "Ocurrio un problema al registrar la entrada";
	public static final String PLACA_COMUN = "198-CA1";
	public static final String PLACA_COMUN_MOTO = "196-AV0";
	public static  final long NUEVE_HORAS = 9;
	public static  final long QUINCE_HORAS = 15;
	public static  final long DIA_EN_HORAS = 24;
	public static  final long TARIFA_DIA_CARRO = 8000;
	public static  final long TARIFA_HORA_CARRO = 1000;
	public static  final long TARIFA_DIA_MOTO = 4000;
	public static  final long TARIFA_HORA_MOTO = 500;
	public static  final long MILISEGUNDOS_HORA = 3600000;
	public static  final int CILINDRAJE_MOTO_500 =	500;
	public static  final int CILINDRAJE_MOTO_550 =	550;
	public static  final long RECARGO_CILINDRAJE_MOTO_500 =	2000;
	private static final Logger logger = LoggerFactory.getLogger(ParqueaderoUtil.class);
	public static  final long MILISEGUNDOS_MINUTO = 60000;
	public static final String SERVICIO_NO_ENCONTRADO = "Servicio no encontrado";
	

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
		return placa.toUpperCase().indexOf('A') == 0;
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
			logger.error(e.getMessage());
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
			logger.error(e.getMessage());
		}
		return date;
	}
	
	public static TiempoServicio calcularTiempoServicio(Calendar fechaEntrada, Calendar fechaSalida) {		
		long totalHorasServicio = ((fechaSalida.getTimeInMillis() -fechaEntrada.getTimeInMillis())/1000)/3600;
		TiempoServicio tiempoServicio = new TiempoServicio(totalHorasServicio,totalHorasServicio);	
		long milisegundosServicio = (fechaSalida.getTimeInMillis() -fechaEntrada.getTimeInMillis());
		long miliSegundosFraccion = milisegundosServicio % ParqueaderoUtil.MILISEGUNDOS_HORA;
		if( miliSegundosFraccion !=0 && miliSegundosFraccion >= ParqueaderoUtil.MILISEGUNDOS_MINUTO ) {
			tiempoServicio.setHoraFraccion(1); 
		}
		if(tiempoServicio.getTotalHoras() == 0 && tiempoServicio.getHoraFraccion() ==1) {
			tiempoServicio.setTotalHoras(1);
			tiempoServicio.setHoras(1);
			tiempoServicio.setHoraFraccion(0);
		}
		return tiempoServicio;
	}
	
	
	public static Calendar getFechaCalendar(String formato , String fecha) {		
		Date date = new Date();
		Calendar fechaCalendar = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formato);			
			date = sdf.parse(fecha);
			
		} catch (Exception e) {		
			logger.error(e.getMessage());
		}
		fechaCalendar.setTime(date);		
		return fechaCalendar;
	}
	
	public static Calendar  restarHorasCalendar(Calendar calendar ,int restar) {
		calendar.add(Calendar.HOUR,restar);
		return calendar;
	}
	
	public static Calendar  restarMinutosCalendar(Calendar calendar ,int restar) {
		calendar.add(Calendar.MINUTE,restar);
		return calendar;
	}

}