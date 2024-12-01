
package usuario;

import java.time.LocalDate;
import java.util.List;

import alquiler.Inmueble;
import sistema.Calificacion;

public interface Propietario {
	public void agregarInmueble(Inmueble inmueble);
	public int cantidadInmueblesAlquilados();
	public List<Inmueble> inmueblesAlquilados();
	public List<Inmueble> getInmuebles();
	public void agregarCalificacionPropietario(Calificacion calificacion);
	public double calcularPromedioPropietario();
	public double calcularPromedioCategoriaPropietario(String categoria);
	public List<Calificacion> getCalificacionesPropietario();
	
	public String getNombre();
	public String getEmail();
	public String getTelefono();
	public LocalDate getFechaRegistro();
	public int calcularDiasDesdeRegistro();
	public boolean tieneReservas(); 
}
