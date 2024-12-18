package usuario;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import alquiler.Inmueble;
import alquiler.Reserva;
import enums.Entidad;
import sistema.Calificacion;
import sistema.GestorCalificaciones;

public class Usuario implements Propietario, Inquilino {
	private String nombre;
	private String email;
	private String telefono;
	private LocalDate fechaRegistro;
	private List<Inmueble> inmuebles;
	private List<Reserva> historialReservas;
	private GestorCalificaciones gestorCalificaciones;
	private List<Calificacion> calificacionesPropietario;
	private List<Calificacion> calificacionesInquilino;
	
	public Usuario(String nombre, String email, String telefono, LocalDate fechaRegistro, GestorCalificaciones gestorCalificaciones) {
		 this.nombre = nombre;
	     this.email = email;
	     this.telefono = telefono;
	     this.fechaRegistro = fechaRegistro;
	     this.inmuebles = new ArrayList<Inmueble>();
	     this.historialReservas = new ArrayList<Reserva>();
	     this.gestorCalificaciones = gestorCalificaciones;
	     this.calificacionesPropietario = new ArrayList<Calificacion>();
	     this.calificacionesInquilino = new ArrayList<Calificacion>();
	}
	
	@Override	
	public String getNombre() {
		return this.nombre;
	}
	
	@Override
	public String getEmail() {
		return this.email;
	}
	
	@Override
	public String getTelefono() {
		return this.telefono;
	}
	
	@Override
	public LocalDate getFechaRegistro() {
		return this.fechaRegistro;
	}
	
	@Override
	public int calcularDiasDesdeRegistro() {
		Period periodo = Period.between(fechaRegistro, LocalDate.now());
		return periodo.getDays();
	}
	
	//Inquilino
	
	@Override
	public void agregarReserva(Reserva reserva) {
		 this.getReservas().add(reserva);

	}

	@Override
	public List<Reserva> getReservas() {
		return this.historialReservas;
	}

	@Override
	public List<Reserva> obtenerReservasFuturas() {
		LocalDate fechaActual = LocalDate.now();
		return this.getReservas().stream().filter(reserva -> reserva.esPosteriorA(fechaActual)).toList();
	}

	@Override
	public List<Reserva> obtenerReservasEnCiudad(String ciudad) {
		return this.getReservas().stream().filter(reserva -> reserva.estaEnCiudad(ciudad)).toList();
	}

	@Override
	public Set<String> obtenerCiudadesConReserva() {
		return this.getReservas().stream().map(reserva -> reserva.getInmueble().getCiudad()).collect(Collectors.toSet());		
	}

	@Override
	public int cantidadReservas() {
		return this.getReservas().size();
	}
	
	@Override
	public void agregarCalificacionInquilino(Calificacion calificacion) {
		gestorCalificaciones.validarCalificacion(calificacion, Entidad.INQUILINO);
		calificacionesInquilino.add(calificacion);	
	}
	
	@Override 
	public double calcularPromedioInquilino() {
		return this.gestorCalificaciones.calcularPromedioGeneral(calificacionesInquilino);
	}
	
	@Override
	public double calcularPromedioCategoriaInquilino(String categoria) {
		this.gestorCalificaciones.validarCategoria(categoria, Entidad.INQUILINO);
		return this.gestorCalificaciones.calcularPromedioDeCategoria(categoria, calificacionesInquilino);
	}
	
	@Override 
	public List<Calificacion> getCalificacionesInquilino(){
		return calificacionesInquilino;
	}
	
	//Propietario

	@Override
	public void agregarInmueble(Inmueble inmueble) {
		this.getInmuebles().add(inmueble);
		
	}

	@Override
	public int cantidadInmueblesAlquilados() {
		return this.inmueblesAlquilados().size();
	}

	@Override
	public List<Inmueble> inmueblesAlquilados() {
		return this.getInmuebles().stream().filter(inmueble -> inmueble.fueAlquilado()).toList();
	}

	@Override
	public List<Inmueble> getInmuebles() {
		return this.inmuebles;
	}

	@Override
	public void agregarCalificacionPropietario(Calificacion calificacion) {
		gestorCalificaciones.validarCalificacion(calificacion, Entidad.PROPIETARIO);
		calificacionesPropietario.add(calificacion);		
	}
	
	
	@Override 
	public double calcularPromedioPropietario() {
		return this.gestorCalificaciones.calcularPromedioGeneral(calificacionesPropietario);
	}
	
	@Override
	public double calcularPromedioCategoriaPropietario(String categoria) {
		this.gestorCalificaciones.validarCategoria(categoria, Entidad.PROPIETARIO);
		return this.gestorCalificaciones.calcularPromedioDeCategoria(categoria, calificacionesPropietario);
	}

	@Override
	public List<Calificacion> getCalificacionesPropietario() {
		return calificacionesPropietario;
	}
	
	@Override
	public boolean tieneReservas() {
		return !historialReservas.isEmpty();
	} 
}