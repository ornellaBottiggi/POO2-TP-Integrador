package sistema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import usuario.Calificable;

public class GestorCalificaciones {
	
	private Map<Calificable, List<Calificacion>> calificacionesEntidades;
	
	public GestorCalificaciones() {
		this.calificacionesEntidades = new HashMap<Calificable, List<Calificacion>>();
	}
	
	public Map<Calificable, List<Calificacion>> getCalificacionesEntidades() {
		return this.calificacionesEntidades;
	}
	
	public void agregarCalificacion(Calificable calificable, Calificacion calificacion) {
		List<Calificacion> calificaciones = calificacionesEntidades.computeIfAbsent(calificable, k -> new ArrayList<Calificacion>());
		//ojo!!! puede romper :p
		calificaciones.add(calificacion);
	}
	
	public List<Calificacion> obtenerCalificaciones(Calificable calificable){
		return this.getCalificacionesEntidades().get(calificable);
	}

	/*public double calcularPromedioGeneral(Calificable calificable) {
	}
	
	public Map<String, double> calcularPromedioCategorias(Calificable calificable) {
	}*/














}
