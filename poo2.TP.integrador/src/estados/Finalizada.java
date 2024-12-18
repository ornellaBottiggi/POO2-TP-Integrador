package estados;

import javax.management.RuntimeErrorException;

import alquiler.Reserva;

public class Finalizada implements EstadoReserva {
	
	@Override
	public void rechazar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido finalizada.");
	}

	@Override
	public void aceptar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido finalizada.");
	}

	@Override
	public void cancelar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido finalizada.");
	}

	@Override
	public void finalizar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido finalizada.");
	}

	@Override
	public boolean permiteCalificaciones() {
		return true;
	}

	@Override
	public boolean esCancelada() {
		return false;
	}

}
