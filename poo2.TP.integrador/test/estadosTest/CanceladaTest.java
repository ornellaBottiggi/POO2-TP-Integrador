package estadosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Reserva;
import estados.Cancelada;
import estados.EstadoReserva;

class CanceladaTest {
	private EstadoReserva cancelada;

	@BeforeEach
	void setUp() {
		cancelada = new Cancelada();
	}

	@Test
	void testAceptarReserva() {
Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { cancelada.aceptar(reserva); });
	}
	
	@Test
	void testCancelarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { cancelada.cancelar(reserva); });
	}
	
	@Test
	void testFinalizarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { cancelada.finalizar(reserva); });
	}
	
	@Test
	void testPermiteCalificaciones() {
		assertFalse(cancelada.permiteCalificaciones());
	}
	
	@Test
	void testSabeSiEsCancelada() {
		assertTrue(cancelada.esCancelada());
	}

}
