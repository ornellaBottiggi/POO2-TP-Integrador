package estadosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Reserva;
import estados.Aceptada;
import estados.Cancelada;
import estados.EstadoReserva;
import estados.Finalizada;

class AceptadaTest {
	private EstadoReserva aceptada;
	
	@BeforeEach
	void setUp() {
		aceptada = new Aceptada();
	}

	@Test
	void testAceptarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { aceptada.aceptar(reserva); });
	}
	
	@Test
	void testCancelarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		aceptada.cancelar(reserva);
		
		verify(reserva).setEstado(any(Cancelada.class));
	}
	
	@Test
	void testFinalizarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		aceptada.finalizar(reserva);
		
		verify(reserva).setEstado(any(Finalizada.class));
	}
	
	@Test
	void testPermiteCalificaciones() {
		assertFalse(aceptada.permiteCalificaciones());
	}
	
	@Test
	void testSabeSiEsCancelada() {
		assertFalse(aceptada.esCancelada());
	}

}
