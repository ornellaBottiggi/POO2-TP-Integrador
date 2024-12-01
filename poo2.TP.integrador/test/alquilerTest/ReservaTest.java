package alquilerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import alquiler.Reserva;
import enums.MetodoPago;
import estados.EstadoReserva;
import usuario.Inquilino;

class ReservaTest {
	private Reserva reserva;
	private EstadoReserva pendiente;
	private Inmueble inmueble;
	private Inquilino inquilino;
	private MetodoPago efectivo;

	@BeforeEach
	void setUp() {
		inquilino = mock(Inquilino.class);
		inmueble = mock(Inmueble.class);
		efectivo = mock(MetodoPago.class);
		// mock para poder testear que le llegan los mensajes al estado de la reserva.
		pendiente = mock(EstadoReserva.class);
				
		reserva = new Reserva(inquilino, inmueble, LocalDate.of(2024, 2, 14), LocalDate.of(2024, 2, 17), efectivo);
		reserva.setEstado(pendiente);
	}
		
	@Test
	void testRegistrarOcupacion() {
		reserva.registrarOcupacion();
		
		verify(pendiente).aceptar(reserva);
	}
	
	@Test
	void testRegistrarCancelacion() {
		reserva.registrarCancelacion();
		
		verify(pendiente).cancelar(reserva);
	}
	
	@Test
	void testRegistrarFinalizacion() {
		reserva.registrarFinalizacion();
		
		verify(pendiente).finalizar(reserva);
	}
	
	@Test
	void testPuedeCalificarse() {
		when(pendiente.permiteCalificaciones()).thenReturn(false);
		
		assertFalse(reserva.puedeCalificarse());
	}
	
	@Test
	void testSeSuperponeConOtroRangoDeFechas() {
		LocalDate fechaDesde = LocalDate.of(2024, 2, 14);
		LocalDate fechaHasta = LocalDate.of(2024, 2, 17);
		
		assertTrue(reserva.seSuperponeCon(fechaDesde, fechaHasta)); 
		
	}
	
	@Test
	void testNoSeSuperponeConOtroRangoDeFechas() {
		LocalDate fechaDesde = LocalDate.of(2024, 2, 10);
		LocalDate fechaHasta = LocalDate.of(2024, 2, 13);
		LocalDate fechaExtraDesde = LocalDate.of(2024, 2, 20);
		LocalDate fechaExtraHasta = LocalDate.of(2024, 2, 22);
		
		assertFalse(reserva.seSuperponeCon(fechaDesde, fechaHasta));
		assertFalse(reserva.seSuperponeCon(fechaExtraDesde, fechaExtraHasta));
	}

	@Test
	void testEstaEnLaCiudadBuscada() {
		when(inmueble.estaUbicadoEn("Quilmes")).thenReturn(true);
		
		assertTrue(reserva.estaEnCiudad("Quilmes"));
	}
	
	@Test
	void testEsPosteriorALaFechaBuscada() {
		LocalDate fechaBuscada = LocalDate.of(2024, 2, 10);
		
		assertTrue(reserva.esPosteriorA(fechaBuscada));
	}
	
	@Test
	void testEstaCancelada() {
		when(pendiente.esCancelada()).thenReturn(false);
		
		assertFalse(reserva.estaCancelada());
	}
	
	@Test
	void testGetters() {
		assertEquals(inquilino, reserva.getInquilino());
		assertEquals(efectivo, reserva.getFormaDePago());
	}
	
	@Test 
	void testRegistrarRechazo() {
		reserva.registrarRechazo();
		
		verify(pendiente).rechazar(reserva);
	}
	
}
