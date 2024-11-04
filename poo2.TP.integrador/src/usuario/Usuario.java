package usuario;

import java.time.LocalDate;
import java.time.Period;

import sistema.Calificacion;
import sistema.SitioWebSAT;

public abstract class Usuario implements Calificable {
	private String nombre;
	private String email;
	private String telefono;
	private LocalDate fechaRegistro;

	public Usuario(String nombre, String email, String telefono, LocalDate fechaRegistro) {
		 this.nombre = nombre;
	     this.email = email;
	     this.telefono = telefono;
	     this.fechaRegistro = fechaRegistro;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getTelefono() {
		return this.telefono;
	}
	
	public LocalDate getFechaRegistro() {
		return this.fechaRegistro;
	}
	
	public int calcularDiasDesdeRegistro() {
		Period periodo = Period.between(fechaRegistro, LocalDate.now());
		return periodo.getDays();
	}
	
	@Override
    public void calificar(SitioWebSAT sitioWeb, Calificable entidad, String categoria, int puntaje, String comentario) {
		Calificacion calificacion = new Calificacion(entidad, categoria, puntaje, comentario);
        sitioWeb.registrarCalificacion(calificacion);
    }
    
}