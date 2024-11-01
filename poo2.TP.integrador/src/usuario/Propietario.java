package usuario;

public class Propietario extends Usuario {
	private List<Inmueble> inmuebles;
	
	public Propietario(String nombre, String email, String telefono, LocalDate fechaRegistro) {
		super(nombre, email, telefono, fechaRegistro)
		this.inmuebles = new ArrayList<Inmueble>();
	}
	
	public void getInmuebles() {
		return this.inmuebles
	}
	
	public void agregarInmueble(Inmueble inmueble) {
		this.getinmuebles.add(inmueble);
	}
	
	public Publicacion generarPublicacion() {
		
	}
	
	public int cantidadDeInmueblesAlquilados() {
      
	}
	
	public List<Inmueble> inmueblesAlquilados(){
		
	}
	
	public void evaluarReserva(Publicacion, Reserva, boolean) {
		
	}
}

