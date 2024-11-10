package estados;

import alquiler.Reserva;

public class Aceptada implements EstadoReserva {

	@Override
	public void aceptar(Reserva reserva) {
		throw new RuntimeException("La reserva ya ha sido aceptada.");
	}

	@Override
	public void cancelar(Reserva reserva) {
		reserva.setEstado(new Cancelada());
	}

	@Override
	public void finalizar(Reserva reserva) {
		reserva.setEstado(new Finalizada());
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
