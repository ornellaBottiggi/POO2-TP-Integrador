package usuario;

public class Inquilino extends Usuario {
	private List<Reserva> historialReservas;
	
	public Inquilino(String nombre, String email, String telefono, LocalDate fechaRegistro) {
        super(nombre, email, telefono, fechaRegistro);
        this.historialReservas = new ArrayList<Reserva>();
    }
	
	public void getHistorialReservas() {
		return this.historialReservas;
	}
	
	public void realizarReserva(Publicacion publicacion, LocalDate fechaInicio, LocalDate fechaFin, MetodoDePago metodoPago) {
	}
	 
	 public void agregarAHistorial(Reserva reserva) {
	        this.getHistorialReservas.add(reserva);
	 }
	 
	 public List<Reserva> obtenerReservas() {
	 }
	 
	 public List<Reserva> obtenerReservasFuturas() {
	 }
	 
	 public List<Reserva> obtenerReservasEnCiudad(String ciudad) {
	 }
	 
	 public Set<String> obtenerCiudadesConReserva() {
	 }
	 
}
