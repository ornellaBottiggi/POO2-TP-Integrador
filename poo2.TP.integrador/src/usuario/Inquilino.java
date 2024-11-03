package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Inquilino extends Usuario {
	private List<Reserva> historialReservas;
	
	public Inquilino(String nombre, String email, String telefono, LocalDate fechaRegistro) {
        super(nombre, email, telefono, fechaRegistro);
        this.historialReservas = new ArrayList<Reserva>();
    }
	
	public List<Reserva> obtenerReservas() {
		return this.historialReservas;
	}
	
	public void realizarReserva(Publicacion publicacion, LocalDate fechaInicio, LocalDate fechaFin, MetodoDePago metodoPago) {
		Inmueble inmuebleReservado = publicacion.getInmueble();
		Reserva reserva = new Reserva(this, inmuebleReservado, fechaInicio, fechaFin, metodoPago);
		this.agregarAHistorial(reserva);
		publicacion.obtenerAprobacionDelPropietario(reserva);
	}
	 
	public void agregarAHistorial(Reserva reserva) {
	    this.obtenerReservas().add(reserva);
	}
	 
	public List<Reserva> obtenerReservasEnCiudad(String ciudad) {
		return this.obtenerReservas().stream().filter(reserva -> reserva.estaEnCiudad(ciudad)).toList();
	}
	 
	public Set<String> obtenerCiudadesConReserva() {
		return this.obtenerReservas().stream().map(reserva -> reserva.getInmueble().getCiudad()).toSet();		
	}
	 
}
