package estadosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Reserva;
import estados.EstadoReserva;
import estados.Finalizada;

class FinalizadaTest {
	private EstadoReserva finalizada;

	@BeforeEach
	void setUp() {
		finalizada = new Finalizada();
	}
	
	@Test 
	void testRechazarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { finalizada.rechazar(reserva); });
	}
		
	@Test
	void testAceptarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { finalizada.aceptar(reserva); });
	}
	
	@Test
	void testCancelarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { finalizada.cancelar(reserva); });
	}
	
	@Test
	void testFinalizarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { finalizada.finalizar(reserva); });
	}
	
	@Test
	void testPermiteCalificaciones() {
		assertTrue(finalizada.permiteCalificaciones());
	}
	
	@Test
	void testSabeSiEsCancelada() {
		assertFalse(finalizada.esCancelada());
	}
	
}
