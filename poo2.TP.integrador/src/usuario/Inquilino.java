package usuario;

import java.util.List;
import java.util.Set;

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
	
}
