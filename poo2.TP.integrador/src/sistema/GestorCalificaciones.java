package sistema;

import java.util.ArrayList;
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
		calificaciones.add(calificacion);
	}
	
	public List<Calificacion> obtenerCalificaciones(Calificable calificable){
		return this.getCalificacionesEntidades().get(calificable);
	}

	public double calcularPromedioGeneral(Calificable calificable) {
		List<Calificacion> calificaciones = obtenerCalificaciones(calificable);
		return calificaciones.stream().mapToInt(calificacion -> calificacion.getPuntaje()).average().orElse(0.0);
	}
	
	public Map<String, Double> calcularPromedioCategorias(Calificable calificable) {
		List<Calificacion> calificaciones = obtenerCalificaciones(calificable);
		Map<String, Double> promedios = new HashMap<String, Double>();
		for (Calificacion calificacion : calificaciones) {
			promedios.put(calificacion.getCategoria(), this.calcularPromedioDeCategoria(calificacion.getCategoria(), calificaciones));
		}
		return promedios;
	}

	private Double calcularPromedioDeCategoria(String categoria, List<Calificacion> calificaciones) {
		return calificaciones.stream()
				.filter(calificacion -> calificacion.esDeCategoria(categoria))
				.mapToInt(calificacion -> calificacion.getPuntaje())
				.average().orElse(0.0);
	}














}
