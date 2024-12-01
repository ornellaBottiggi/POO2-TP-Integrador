package estadosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import alquiler.Reserva;
import estados.Aceptada;
import estados.EstadoReserva;
import estados.Pendiente;
import estados.Rechazada;

class PendienteTest {
	private EstadoReserva pendiente;

	@BeforeEach
	void setUp() {	
		pendiente = new Pendiente();		
	}
	
	@Test 
	void testRechazarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		pendiente.rechazar(reserva);	
	
		verify(reserva).setEstado(any(Rechazada.class));	
	}
	
	@Test
	void testAceptarReserva() {
		Inmueble inmueble = mock(Inmueble.class);
		Reserva reserva = mock(Reserva.class);
		when(reserva.getInmueble()).thenReturn(inmueble);
							
		pendiente.aceptar(reserva);
			
		verify(reserva.getInmueble()).incrementarVecesAlquilado();
		verify(reserva).setEstado(any(Aceptada.class));
	}
	
	@Test
	void testCancelarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { pendiente.cancelar(reserva); });
	}
	
	@Test
	void testFinalizarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		assertThrows(RuntimeException.class, () -> { pendiente.finalizar(reserva); });
	}
	
	@Test
	void testPermiteCalificaciones() {
		assertFalse(pendiente.permiteCalificaciones());
	}
	
	@Test
	void testSabeSiEsCancelada() {
		assertFalse(pendiente.esCancelada());
	}

}
