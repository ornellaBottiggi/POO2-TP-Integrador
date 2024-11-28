package estados;

import alquiler.Reserva;

public class Cancelada implements EstadoReserva {

	@Override
	public void rechazar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido cancelada.");		
	}
	
	@Override
	public void aceptar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido cancelada.");
	}

	@Override
	public void cancelar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido cancelada.");
	}

	@Override
	public void finalizar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido cancelada.");
	}

	@Override
	public boolean permiteCalificaciones() {
		return false;
	}

	@Override
	public boolean esCancelada() {
		return true;
	}
	
}
