package alquiler;

import java.time.LocalDate;

import enums.MetodoPago;
import estados.EstadoReserva;
import estados.Pendiente;
import usuario.Inquilino;

public class Reserva {
	private Inquilino inquilino;
	private Inmueble inmueble;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private MetodoPago formaDePago;
	private EstadoReserva estado;
	
	public Reserva(Inquilino inquilino, Inmueble inmueble, LocalDate fechaInicio, LocalDate fechaFin, MetodoPago formaDePago) {
		this.inquilino = inquilino;
		this.inmueble = inmueble;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.formaDePago = formaDePago;
		this.estado = new Pendiente();
	}
	
	public Inquilino getInquilino() {
		return this.inquilino;
	}
	
	public Inmueble getInmueble() {
		return this.inmueble;
	}
	
	public LocalDate getFechaInicio() {
		return this.fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}
	
	public MetodoPago getFormaDePago() {
		return this.formaDePago;
	}
	
	public EstadoReserva getEstado() {
		return this.estado;
	}
	
	public void setEstado(EstadoReserva estadoNuevo) {
		this.estado = estadoNuevo;
	}
	
	public void registrarOcupacion() {
		this.getEstado().aceptar(this);
	}
	
	public void registrarCancelacion() {
		this.getEstado().cancelar(this);
	}
	
	public void registrarFinalizacion() {
		this.getEstado().finalizar(this);
	}
	
	public boolean puedeCalificarse() {
		return this.getEstado().permiteCalificaciones();
	}
	
	public boolean seSuperponeCon(LocalDate fechaDesde, LocalDate fechaHasta) {
		return fechaDesde.isBefore(this.getFechaFin()) && fechaHasta.isAfter(this.getFechaInicio());
	}
	
	public boolean estaEnCiudad(String ciudad) {
		return this.getInmueble().estaUbicadoEn(ciudad);
	}

	public boolean esPosteriorA(LocalDate fecha) {
		return this.getFechaInicio().isAfter(fecha);
	}

	public boolean estaCancelada() {
		return this.getEstado().esCancelada();
	}
}
