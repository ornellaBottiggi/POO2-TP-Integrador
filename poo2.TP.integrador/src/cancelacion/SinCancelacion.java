package cancelacion;

import java.time.LocalDate;

import alquiler.Publicacion;
import alquiler.Reserva;

public class SinCancelacion implements PoliticaCancelacion {

	@Override
	public double calcularRetencion(LocalDate fechaCancelacion, Reserva reserva, Publicacion publicacion) {
		return publicacion.precioAlquiler(reserva.getFechaInicio(), reserva.getFechaFin());
	}

}
