package cancelacion;

import java.time.LocalDate;

import alquiler.Publicacion;
import alquiler.Reserva;

public interface PoliticaCancelacion {
	
	public double calcularRetencion(LocalDate fechaCancelacion, Reserva reserva, Publicacion publicacion) 
	
}
