package sistema;

public class Calificacion {
	private Calificable entidadPuntuada;
	private String categoria;
	private int puntaje;
	private String comentario;
	
	public Calificacion(Calificable entidad, String categoria, int puntaje, String comentario) {
		this.entidadPuntuada = entidad;
		this.categoria = categoria;
		this.puntaje = puntaje;
		this.comentario = comentario;
	}
	
	public Calificable getEntidadPuntuada() {
		return this.entidadPuntuada;
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



}
	
