
package usuario;

import java.util.List;

import alquiler.Inmueble;
import sistema.Calificacion;

public interface Propietario {
	public void agregarInmueble(Inmueble inmueble);
	public int cantidadInmueblesAlquilados();
	public List<Inmueble> inmueblesAlquilados();
	public List<Inmueble> getInmuebles();
	public void agregarCalificacionPropietario(Calificacion calificacion);
}
