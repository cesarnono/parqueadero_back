package com.ceiba.induccion.parqueadero.model;

import java.util.Calendar;

import com.ceiba.induccion.parqueadero.service.ServicioTestDataBuilder;
import com.ceiba.induccion.parqueadero.util.ParqueaderoUtil;

public class Cobro {

	private long id;
	private Calendar fechaEntrada;
	private Calendar fechaSalida;
	private String estado;
	protected long valorServicio;
	protected long tiempoServicioHoras;
	protected String descripcionTiempoServicio;
	private Servicio servicio;
	private String error;

	public Cobro() {

	}

	public Cobro(long id, Calendar fechaEntrada, Calendar fechaSalida, String estado, long valorServicio,
			long tiempoServicio, String descripcionTiempoServicio, Servicio servicio) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.estado = estado;
		this.valorServicio = valorServicio;
		this.tiempoServicioHoras = tiempoServicio;
		this.descripcionTiempoServicio = descripcionTiempoServicio;
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

	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setValorServicio(long valorServicio) {
		this.valorServicio = valorServicio;
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

	public static void main(String[] args) {
		Servicio servicio = new ServicioTestDataBuilder().build();
		CobroCarro cobroCarro = new CobroCarro(-1, null,
				ParqueaderoUtil.getFechaCalendar("dd-M-yyyy HH:mm:ss", "18-03-2018 01:00:00"),
				ParqueaderoUtil.getFechaCalendar("dd-M-yyyy HH:mm:ss", "20-03-2018 14:10:00"), null, 0, 0, null,
				servicio);
		cobroCarro.calcularValorServicio();
		System.out.println(cobroCarro.getDescripcionTiempoFacturado());

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
				tiempoServicio.getTotalHoras() >horasAcumuladasDias	&& tiempoServicio.getTotalHoras() < horasAcumuladasDias	+ParqueaderoUtil.NUEVE_HORAS) {
			valorServicio = valorServicio + (tiempoServicio.getHoraFraccion() * this.getServicio().getTarifaHora());
		}
		return valorServicio;
	}

}
