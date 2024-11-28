package sistema;

import alquiler.Reserva;

public class Calificacion {
	private Reserva reservaCalificada;
	private String categoria;
	private int puntaje;
	private String comentario;
	
	public Calificacion(Reserva reservaCalificada, String categoria, int puntaje, String comentario) {
		this.reservaCalificada = reservaCalificada;
		this.categoria = categoria;
		this.puntaje = puntaje;
		this.comentario = comentario;
	}
	
	public Reserva getReserva() {
		return this.reservaCalificada;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
	
	public String getComentario() {
		return this.comentario;
	}

	public boolean esDeCategoria(String categoria) {
		return this.getCategoria().equals(categoria);
	}

}
	
