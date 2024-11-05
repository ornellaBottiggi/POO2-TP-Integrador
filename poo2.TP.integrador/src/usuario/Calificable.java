package usuario;

import alquiler.Reserva;
import sistema.SitioWebSAT;

public interface Calificable {
	public void calificar(SitioWebSAT sitioWeb, Reserva reserva, Calificable entidad, String categoria, int puntaje, String comentario);

}
