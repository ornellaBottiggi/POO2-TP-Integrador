package estadosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Reserva;
import estados.EstadoReserva;
import estados.Rechazada;

class RechazadaTest {

	private EstadoReserva rechazada;

	@BeforeEach
	void setUp() {
		rechazada = new Rechazada();
	}

	@Test
	void testRechazarReserva() {
		Reserva reserva = mock(Reserva.class);
	
		assertThrows(RuntimeException.class, () -> { rechazada.rechazar(reserva); });
	}
	
	@Test
	void testAceptarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { rechazada.aceptar(reserva); });
	}
	
	@Test
	void testCancelarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { rechazada.cancelar(reserva); });
	}
	
	@Test
	void testFinalizarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { rechazada.finalizar(reserva); });
	}
	
	@Test
	void testPermiteCalificaciones() {
		assertFalse(rechazada.permiteCalificaciones());
	}
	
	@Test
	void testSabeSiEsCancelada() {
		assertFalse(rechazada.esCancelada());
	}
	

}
