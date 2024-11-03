package usuario;

import sistema.SitioWebSAT;

public interface Calificable {
	public void calificar(SitioWebSAT sitioWeb, Calificable entidad, String categoria, int puntaje, String comentario);
}
