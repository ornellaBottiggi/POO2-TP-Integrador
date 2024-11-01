package usuario;

public class Usuario implements Calificable {
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
	
	/*@Override
    public Calificacion calificar(Calificable entidad, String comentario, int puntaje, String categoria) {
        return new Calificacion(entidad, this, categoria, puntaje, comentario);
    }
    Mel mira esto q no sabemos con orne :(
    */
}