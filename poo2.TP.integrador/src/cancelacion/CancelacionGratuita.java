package cancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import alquiler.Publicacion;
import alquiler.Reserva;

public class CancelacionGratuita implements PoliticaCancelacion {

	@Override
	public double calcularRetencion(LocalDate fechaCancelacion, Reserva reserva, Publicacion publicacion) {
		long diasAnticipacion = ChronoUnit.DAYS.between(fechaCancelacion, reserva.getFechaInicio());
		return (diasAnticipacion <= 10) ? 0 : publicacion.getPrecioBase() * 2;
	
	}
	
}
