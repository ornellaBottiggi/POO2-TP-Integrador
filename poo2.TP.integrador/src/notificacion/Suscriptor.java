package notificacion;

import alquiler.Publicacion;
import alquiler.Reserva;

public interface Suscriptor {
	
	public void cambioDePrecio(Publicacion publicacion, double nuevoPrecio);
	public void cancelacionReserva(Reserva reserva);
	public void reservaInmueble(Reserva reserva);
	
}
