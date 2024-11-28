package notificacion;

import alquiler.Publicacion;
import alquiler.Reserva;

public interface Suscriptor {
	
	public void cambioDePrecio(Publicacion publicacion);
	public void cancelacionReserva(Reserva reserva);
	public void reservaInmueble(Reserva reserva);
	
}
