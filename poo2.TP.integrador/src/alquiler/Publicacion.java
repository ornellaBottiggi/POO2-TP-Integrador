package alquiler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import cancelacion.PoliticaCancelacion;
import enums.MetodoPago;
import notificacion.Notificador;
import notificacion.Suscriptor;
import usuario.Propietario;

public class Publicacion implements Notificador {
	private Propietario propietario;
	private Inmueble inmueble;
	private LocalTime checkIn;
	private LocalTime checkOut;
	private List<MetodoPago> formasDePago;
	private double precioBase;
	private List<PrecioTemporada> preciosTemporada;
	private List<Reserva> reservasActuales;
	private Queue<Reserva> listaDeEspera;
	private PoliticaCancelacion politicaCancelacion;
	private List<Suscriptor> suscriptores;
	
	public Publicacion(Propietario propietario, Inmueble inmueble, LocalTime checkin, LocalTime checkout, List<MetodoPago> formasDePago, double precio) {
		this.propietario = propietario;
		this.inmueble = inmueble;
		this.checkIn = checkin;
		this.checkOut = checkout;
		this.formasDePago = formasDePago;
		this.precioBase = precio;
		this.preciosTemporada = new ArrayList<PrecioTemporada>();
		this.reservasActuales = new ArrayList<Reserva>();
		this.listaDeEspera = new ArrayDeque<Reserva>();
		this.suscriptores = new ArrayList<Suscriptor>();
	}
	
	public Propietario getPropietario() {
		return this.propietario;
	}
	
	public Inmueble getInmueble() {
		return this.inmueble;
	}
	
	public LocalTime getCheckIn() {
		return this.checkIn;
	}
	
	public LocalTime getCheckOut() {
		return this.checkOut;
	}
	
	public List<MetodoPago> getFormasDePago() {
		return this.formasDePago;
	}
	
	public double getPrecioBase() {
		return this.precioBase;
	}
	
	public List<PrecioTemporada> getPreciosTemporada() {
		return this.preciosTemporada;
	}
	
	public List<Reserva> getReservasActuales() {
		return this.reservasActuales;
	}
	
	public Queue<Reserva> getListaDeEspera() {
		return this.listaDeEspera;
	}
	
	public PoliticaCancelacion getPoliticaCancelacion() {
		return this.politicaCancelacion;
	}
	
	public List<Suscriptor> getSuscriptores() {
		return this.suscriptores;
	}
	
	public void setPoliticaCancelacion(PoliticaCancelacion cancelacion) {
		this.politicaCancelacion = cancelacion;
	}
	
	public void setPrecioBase(double precio) {
		this.precioBase = precio;
	}
	
	public void agregarPrecioTemporada(PrecioTemporada precio) {
		this.getPreciosTemporada().add(precio);
	}
	
	public double precioAlquiler(LocalDate fechaDesde, LocalDate fechaHasta) {
		double total = 0;
		for(LocalDate fecha = fechaDesde; !fecha.isAfter(fechaHasta); fecha = fecha.plusDays(1)) {
			total += this.calcularPrecioParaFecha(fecha);
		}
		return total;
	}
	
	public double calcularPrecioParaFecha(LocalDate fecha) {
		double precioFinal = this.getPrecioBase();
		for(PrecioTemporada precio : preciosTemporada) {
			if(precio.pertenece(fecha)) {
				precioFinal = precio.getPrecio();
				break;
			}
		}
		return precioFinal;
	}
	
	/*
	private double calcularPrecioParaFecha(LocalDate fecha) {
		return this.getPreciosTemporada().stream()
				.filter(precio -> precio.pertenece(fecha))
				.mapToDouble(precio -> precio.getPrecio())
				.findFirst()
				.orElse(this.getPrecioBase());
	}
	*/

	public void cambiarPrecio(double nuevoPrecio) {
		double precioViejo = this.getPrecioBase();
		this.setPrecioBase(nuevoPrecio);
		if(nuevoPrecio < precioViejo) {
			this.notificarBajaDePrecio();
		}
	}
	
	public void obtenerAprobacionDelPropietario(Reserva reserva) {
		this.getPropietario().aprobarReserva(this, reserva);
	}
	
	public boolean estaDisponible(LocalDate fechaDesde, LocalDate fechaHasta) {
		return this.getReservasActuales().stream().noneMatch(reserva -> reserva.seSuperponeCon(fechaDesde, fechaHasta));
	}
	
	public void procesarReserva(Reserva reserva) {
		if(this.estaDisponible(reserva.getFechaInicio(), reserva.getFechaFin())) {
			this.asentarReserva(reserva);
			reserva.registrarOcupacion();
		} else {
			this.agregarAListaDeEspera(reserva); // se toma como una reserva condicional
		}
	}
	
	private void asentarReserva(Reserva reserva) {
		this.getReservasActuales().add(reserva);
		this.notificarReserva(reserva);
	}
	
	private void agregarAListaDeEspera(Reserva reserva) {
		this.getListaDeEspera().add(reserva);
	}
	
	public void cancelarReserva(Reserva reserva) {
		reserva.registrarCancelacion();
		this.eliminarReserva(reserva);
		this.procesarListaDeEspera(reserva.getFechaInicio(), reserva.getFechaFin());
		this.notificarCancelacionDeReserva(reserva);
	}
	
	private void eliminarReserva(Reserva reserva) {
		this.getReservasActuales().remove(reserva);
	}
	
	private void procesarListaDeEspera(LocalDate fechaDesdeCancelada, LocalDate fechaHastaCancelada) {
		Optional<Reserva> reservaEnEspera = this.buscarReservaEnEspera(fechaDesdeCancelada, fechaHastaCancelada);
		reservaEnEspera.ifPresent(reserva -> {
			this.asentarReserva(reserva);
			this.eliminarReservaEnEspera(reserva);
		});
	}
	
	private Optional<Reserva> buscarReservaEnEspera(LocalDate fechaDesde, LocalDate fechaHasta) {
		return this.getListaDeEspera().stream().filter(reserva -> reserva.seSuperponeCon(fechaDesde, fechaHasta)).findFirst();
	}
	
	private void eliminarReservaEnEspera(Reserva reserva) {
		this.getListaDeEspera().remove(reserva);
	}
	
	public void finalizarReserva(Reserva reserva) {
		reserva.registrarFinalizacion();
		this.eliminarReserva(reserva);
		this.limpiarReservasCondicionales(reserva.getFechaInicio(), reserva.getFechaFin());
	}
	
	private void limpiarReservasCondicionales(LocalDate fechaDesde, LocalDate fechaHasta) {
		this.getListaDeEspera().removeIf(reserva -> reserva.seSuperponeCon(fechaDesde, fechaHasta));
	}
	
	public double retencionPorCancelacion(Reserva reserva) {
		if(!reserva.estaCancelada()) {
			throw new RuntimeException("La reserva no está cancelada, no se puede calcular la retención."); 
		}
		return this.getPoliticaCancelacion().calcularRetencion(LocalDate.now(), reserva, this);
	}

	public boolean tieneReservas() {
		return !getReservasActuales().isEmpty();
	}

	@Override
	public void agregarSuscriptor(Suscriptor suscriptor) {
		this.getSuscriptores().add(suscriptor);
	}

	@Override
	public void eliminarSuscriptor(Suscriptor suscriptor) {
		this.getSuscriptores().remove(suscriptor);		
	}

	@Override
	public void notificarBajaDePrecio() {
		for(Suscriptor suscriptor : suscriptores) {
	        suscriptor.cambioDePrecio(this, this.getPrecioBase());
	    }
	}

	@Override
	public void notificarCancelacionDeReserva(Reserva reserva) {
		for(Suscriptor suscriptor : suscriptores) {
			suscriptor.cancelacionReserva(reserva);
		}
	}

	@Override
	public void notificarReserva(Reserva reserva) {
		for(Suscriptor suscriptor : suscriptores) {
			suscriptor.reservaInmueble(reserva);
		}
	}
}
