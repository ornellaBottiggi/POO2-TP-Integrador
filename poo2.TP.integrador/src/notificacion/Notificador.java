package notificacion;

import alquiler.Reserva;

public interface Notificador {
	
	public void agregarSuscriptor(Suscriptor suscriptor);
	public void eliminarSuscriptor(Suscriptor suscriptor);
	public void notificarBajaDePrecio();
	public void notificarCancelacionDeReserva(Reserva reserva);
	public void notificarReserva(Reserva reserva);
	
}
