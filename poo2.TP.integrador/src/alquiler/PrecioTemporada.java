package alquiler;

import java.time.LocalDate;

public class PrecioTemporada {
	private String temporada;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private double precio;
	
	public PrecioTemporada(String temp, LocalDate fechaInicio, LocalDate fechaFin, double precio) {
		this.temporada = temp;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
	}
	
	public String getTemporada() {
		return this.temporada;
	}
	
	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	
	public double getPrecio() {
		return this.precio;
	}
	
	public boolean pertenece(LocalDate fecha) {
		return fecha.isEqual(this.getFechaInicio()) || fecha.isEqual(this.getFechaFin()) || fecha.isAfter(this.getFechaInicio()) && fecha.isBefore(this.getFechaFin());
	}
}
