package sistema;


import java.util.List;


import alquiler.Reserva;
import enums.Entidad;


public class GestorCalificaciones {
	
	private SitioWebSAT sitioWeb;
	
	public GestorCalificaciones(SitioWebSAT sitioWeb) {
		this.sitioWeb = sitioWeb;
	}
	
	public void validarCalificacion(Calificacion calificacion, Entidad entidad) {
		Reserva reserva = calificacion.getReserva();
		String categoria = calificacion.getCategoria();
		if (!this.cumplenRequisitos(reserva, categoria, entidad)) {
			throw new RuntimeException("La calificacion no es válida");
		}
	}
	
	public boolean cumplenRequisitos(Reserva reservaCalificada, String categoriaCalificada, Entidad entidad) {
		return reservaCalificada.puedeCalificarse() & this.sitioWeb.esCategoriaValida(categoriaCalificada, entidad);
	}
	
	public double calcularPromedioGeneral(List<Calificacion> calificaciones) {
		return calificaciones.stream().mapToInt(calificacion -> calificacion.getPuntaje()).average().orElse(0.0);
	}
	
	public double calcularPromedioDeCategoria(String categoria, List<Calificacion> calificaciones) {
		return calificaciones.stream()
				.filter(calificacion -> calificacion.esDeCategoria(categoria))
				.mapToInt(calificacion -> calificacion.getPuntaje())
				.average().orElse(0.0);
	}

	public void validarCategoria(String categoria, Entidad entidad) {
		if (!this.sitioWeb.esCategoriaValida(categoria, entidad)) {
			throw new RuntimeException("La categoria no es válida.");
		}
	}
	
}
