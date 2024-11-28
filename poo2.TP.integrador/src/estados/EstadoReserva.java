package estados;

import alquiler.Reserva;

public interface EstadoReserva {
	
	public void rechazar(Reserva reserva);
	public void aceptar(Reserva reserva);
	public void cancelar(Reserva reserva);
	public void finalizar(Reserva reserva);
	public boolean permiteCalificaciones();
	public boolean esCancelada();
	
}
