package usuario;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

import org.mockito.ArgumentMatchers;

import alquiler.Reserva;
import sistema.Calificacion;

public interface Inquilino {
	public void agregarReserva(Reserva reserva);
	public List<Reserva> getReservas();
	public List<Reserva> obtenerReservasFuturas();
	public List<Reserva> obtenerReservasEnCiudad(String ciudad);
	public Set<String> obtenerCiudadesConReserva();
	public int cantidadReservas();
	public void agregarCalificacionInquilino(Calificacion calificacion);
	public double calcularPromedioInquilino();
	public double calcularPromedioCategoriaInquilino(String categoria);
	public List<Calificacion> getCalificacionesInquilino();
	
	public String getNombre();
	public String getEmail();
	public String getTelefono();
	public LocalDate getFechaRegistro();
	public int calcularDiasDesdeRegistro();
	public boolean tieneReservas();
}
