package usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import alquiler.Inmueble;
import alquiler.Publicacion;
import alquiler.Reserva;
import enums.MetodoPago;
import sistema.SitioWebSAT;

public class Propietario extends Usuario {
	private List<Inmueble> inmuebles;
	
	public Propietario(String nombre, String email, String telefono, LocalDate fechaRegistro) {
		super(nombre, email, telefono, fechaRegistro);
		this.inmuebles = new ArrayList<Inmueble>();
	}
	
	public List<Inmueble> getInmuebles() {
		return this.inmuebles;
	}
	
	public void agregarInmueble(Inmueble inmueble) {
		this.getInmuebles().add(inmueble);
	}
	
	public void generarPublicacion(SitioWebSAT sitioWeb, Inmueble inmueble, LocalTime checkIn, LocalTime checkOut, List<MetodoPago> metodoPago, double precioBase) {
		Publicacion publicacion = new Publicacion (this, inmueble, checkIn, checkOut, metodoPago, precioBase);
		sitioWeb.altaDePublicacion(publicacion);	
	}
	
	public List<Inmueble> inmueblesAlquilados(){
		return this.getInmuebles().stream().filter(inmueble -> inmueble.fueAlquilado()).toList();
	}
	
	public int cantidadDeInmueblesAlquilados() {
		return this.inmueblesAlquilados().size();
	}
	
	public void aprobarReserva(Publicacion publicacion, Reserva reserva) {
		publicacion.procesarReserva(reserva);
	}
}

