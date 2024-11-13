package cancelacionTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Publicacion;
import alquiler.Reserva;
import cancelacion.PoliticaCancelacion;
import cancelacion.SinCancelacion;

class SinCancelacionTest {
	private PoliticaCancelacion sinCancelacion;
	private Publicacion publicacion;
		
	@BeforeEach
	void setUp() {
		this.sinCancelacion = new SinCancelacion();
		this.publicacion = mock(Publicacion.class);
		when(publicacion.getPrecioBase()).thenReturn(50d);
	}

	@Test
	void testCalcularRetencion() {
		LocalDate fechaCancelacion = LocalDate.of(2024, 11, 1);
		LocalDate fechaInicio = LocalDate.of(2024, 11, 11);
		LocalDate fechaFin = LocalDate.of(2024, 11, 13);
		Reserva reserva = mock(Reserva.class);
		when(reserva.getFechaInicio()).thenReturn(fechaInicio);
		when(reserva.getFechaFin()).thenReturn(fechaFin);
		when(publicacion.precioAlquiler(fechaInicio, fechaFin)).thenReturn(150d);
		
		double retencion = sinCancelacion.calcularRetencion(fechaCancelacion, reserva, publicacion);
		
		assertEquals(150.0, retencion);
	
	}

}
