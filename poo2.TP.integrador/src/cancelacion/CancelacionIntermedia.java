package cancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import alquiler.Publicacion;
import alquiler.Reserva;

public class CancelacionIntermedia implements PoliticaCancelacion{

	@Override
	public double calcularRetencion(LocalDate fechaCancelacion, Reserva reserva, Publicacion publicacion) {
		long diasAnticipacion = ChronoUnit.DAYS.between(fechaCancelacion, reserva.getFechaInicio());
		double precioTotal = publicacion.precioAlquiler(reserva.getFechaInicio(), reserva.getFechaFin());
		return (diasAnticipacion >= 20) ? 0 : this.retencionSobrePrecio(diasAnticipacion, precioTotal);
	}
	
	private double retencionSobrePrecio(long diasAnticipacion, double precioTotal) {
		return (diasAnticipacion <= 10) ? precioTotal : precioTotal * 0.5;
	}

}