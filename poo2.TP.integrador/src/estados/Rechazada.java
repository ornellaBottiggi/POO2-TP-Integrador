package estados;

import alquiler.Reserva;

public class Rechazada implements EstadoReserva {
	
	@Override
	public void rechazar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido rechazada.");
	}

	@Override
	public void aceptar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido rechazada.");
	}

	@Override
	public void cancelar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido rechazada.");		
	}

	@Override
	public void finalizar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido rechazada.");
	}

	@Override
	public boolean permiteCalificaciones() {
		return false;
	}

	@Override
	public boolean esCancelada() {
		return false;
	}

}
