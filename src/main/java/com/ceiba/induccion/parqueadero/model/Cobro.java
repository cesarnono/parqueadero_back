package com.ceiba.induccion.parqueadero.model;

import java.util.Calendar;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;

public class Cobro {

	private long id;
	private Calendar fechaEntrada;
	protected Calendar fechaSalida;
	private String estado;
	protected long valorServicio;
	protected long tiempoServicioHoras;
	protected String descripcionTiempoServicio;
	private Servicio servicio;
	private String error;

	public Cobro() {

	}

	public Cobro(long id, Calendar fechaEntrada, Calendar fechaSalida, String estado, long valorServicio,
			 Servicio servicio) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.estado = estado;
		this.valorServicio = valorServicio;		
		this.servicio = servicio;
		this.id = id;	
	}

	public String getEstado() {
		return estado;
	}

	public long getValorServicio() {
		return valorServicio;
	}

	public long getTiempoServicioHoras() {
		return tiempoServicioHoras;
	}

	public String getDescripcionTiempoServicio() {
		return descripcionTiempoServicio;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public Calendar getFechaEntrada() {
		return fechaEntrada;
	}

	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public void calcularValorServicio() {		
		this.fechaSalida = Calendar.getInstance();
		TiempoServicio tiempoServicio = ParqueaderoUtil.calcularTiempoServicio(this.getFechaEntrada(),
				this.getFechaSalida());
		long valorTotalServicio = this.calcularValorServicioRecursivo(tiempoServicio, 0);
		this.valorServicio = this.aplicarHorasFraccion(tiempoServicio, valorTotalServicio);
		this.descripcionTiempoServicio = tiempoServicio.obtenerTiempoFacturado();
		this.tiempoServicioHoras = tiempoServicio.getTotalHoras() + tiempoServicio.getHoraFraccion();
		
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long calcularValorServicioRecursivo(TiempoServicio tiempoServicio, long dias) {
		long valor = 0;
		long rangoCobroHoras = ParqueaderoUtil.NUEVE_HORAS + (ParqueaderoUtil.DIA_EN_HORAS * dias);
		long rangoCobroDias = ParqueaderoUtil.DIA_EN_HORAS + (ParqueaderoUtil.DIA_EN_HORAS * dias);
		if (rangoCobroHoras > tiempoServicio.getTotalHoras()) {
			valor = (tiempoServicio.getTotalHoras() - (ParqueaderoUtil.DIA_EN_HORAS * dias))
					* this.getServicio().getTarifaHora();
		} else if (rangoCobroDias > tiempoServicio.getTotalHoras()) {
			valor = this.getServicio().getTarifaDia();
			tiempoServicio.setDias(tiempoServicio.getDias() + 1);
			tiempoServicio.setHoras(0);
		} else {
			dias = dias + 1;
			tiempoServicio.setDias(tiempoServicio.getDias() + 1);
			tiempoServicio.setHoras(tiempoServicio.getHoras() - ParqueaderoUtil.DIA_EN_HORAS);
			valor = this.getServicio().getTarifaDia() + calcularValorServicioRecursivo(tiempoServicio, dias);
		}
		return valor;
	}	

	public String getDescripcionTiempoFacturado() {
		StringBuilder consola = new StringBuilder();
		consola.append("============FACTURACION============\n");
		consola.append("TOTAL HORAS: " + this.getTiempoServicioHoras() + "\n");
		consola.append("TOTAL SERVICIO FORMATEADO: " + this.getDescripcionTiempoServicio() + "\n");
		consola.append("VALOR SERVICIO: " + this.valorServicio + "\n");
		return consola.toString();
	}

	public long aplicarHorasFraccion(TiempoServicio tiempoServicio, long valorServicio) {
		
		long horasAcumuladasDias = (tiempoServicio.getDias() * ParqueaderoUtil.DIA_EN_HORAS);
		if (tiempoServicio.getHoraFraccion() != 0 &&
				tiempoServicio.getTotalHoras() >= horasAcumuladasDias	&& tiempoServicio.getTotalHoras() < horasAcumuladasDias	+ParqueaderoUtil.NUEVE_HORAS) {
			valorServicio = valorServicio + (tiempoServicio.getHoraFraccion() * this.getServicio().getTarifaHora());
		}
		return valorServicio;
	}

}
