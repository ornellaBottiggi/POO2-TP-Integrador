
package cancelacionTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Publicacion;
import alquiler.Reserva;
import cancelacion.CancelacionGratuita;
import cancelacion.PoliticaCancelacion;

class CancelacionGratuitaTest {
	private PoliticaCancelacion cancelacionGratuita;
	private Publicacion publicacion;
	
	@BeforeEach
	void setUp() {
		this.cancelacionGratuita = new CancelacionGratuita();
		this.publicacion = mock(Publicacion.class);
		when(publicacion.getPrecioBase()).thenReturn(50d);
	}

	@Test
	void testCalcularRetencionPorMenosDe10Dias() {
		LocalDate fechaCancelacion = LocalDate.of(2024, 11, 1);
		LocalDate fechaInicioReserva = LocalDate.of(2024, 11, 6);
		Reserva reserva = mock(Reserva.class);
		when(reserva.getFechaInicio()).thenReturn(fechaInicioReserva);
		
		double retencion = cancelacionGratuita.calcularRetencion(fechaCancelacion, reserva, publicacion);
		
		assertEquals(0, retencion);
		
	}
	
	@Test 
	void testCalcularRetencionPorMasDe10Dias() {
		LocalDate fechaCancelacion = LocalDate.of(2024, 11, 1);
		LocalDate fechaInicioReserva = LocalDate.of(2024, 11, 23);
		Reserva reserva = mock(Reserva.class);
		when(reserva.getFechaInicio()).thenReturn(fechaInicioReserva);
		
		double retencion = cancelacionGratuita.calcularRetencion(fechaCancelacion, reserva, publicacion);
		
		assertEquals(100, retencion);
		
	}

}
