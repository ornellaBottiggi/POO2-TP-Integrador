package estados;

import alquiler.Reserva;

public class Pendiente implements EstadoReserva {

	@Override
	public void aceptar(Reserva reserva) {
		reserva.getInmueble().incrementarVecesAlquilado();
		reserva.setEstado(new Aceptada());
	}

	@Override
	public void cancelar(Reserva reserva) {
		throw new RuntimeException("La reserva no puede ser cancelada si aún está pendiente.");
	}

	@Override
	public void finalizar(Reserva reserva) {
		throw new RuntimeException("La reserva no puede ser finalizada si aún está pendiente.");		
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
