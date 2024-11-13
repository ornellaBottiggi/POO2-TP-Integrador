package cancelacionTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Publicacion;
import alquiler.Reserva;
import cancelacion.CancelacionIntermedia;
import cancelacion.PoliticaCancelacion;

class CancelacionIntermediaTest {
	private PoliticaCancelacion cancelacionIntermedia;
	private Publicacion publicacion;
	
	@BeforeEach
	void setUp() {
		this.cancelacionIntermedia = new CancelacionIntermedia();
		this.publicacion = mock(Publicacion.class);
		when(publicacion.getPrecioBase()).thenReturn(50d);
	}

	@Test
	void testCalcularRetencionMasDe20Dias() {
		LocalDate fechaCancelacion = LocalDate.of(2024, 11, 1);
		LocalDate fechaInicio = LocalDate.of(2024, 11, 25);
		LocalDate fechaFin = LocalDate.of(2024, 11, 27);
		Reserva reserva = mock(Reserva.class);
		when(reserva.getFechaInicio()).thenReturn(fechaInicio);
		when(reserva.getFechaFin()).thenReturn(fechaFin);
	
		double retencion = cancelacionIntermedia.calcularRetencion(fechaCancelacion, reserva, publicacion);
		
		assertEquals(0, retencion);
	}
	
	@Test 
	void testCalcularRetencionMenosDe10Dias() {

		LocalDate fechaCancelacion = LocalDate.of(2024, 11, 1);
		LocalDate fechaInicio = LocalDate.of(2024, 11, 11);
		LocalDate fechaFin = LocalDate.of(2024, 11, 27);
		Reserva reserva = mock(Reserva.class);
		when(reserva.getFechaInicio()).thenReturn(fechaInicio);
		when(reserva.getFechaFin()).thenReturn(fechaFin);
		when(publicacion.precioAlquiler(fechaInicio, fechaFin)).thenReturn(850d);

		double retencion = cancelacionIntermedia.calcularRetencion(fechaCancelacion, reserva, publicacion);
		
		
		assertEquals(850.0, retencion);
	}
	
	@Test 
	void testCalcularRetencionMasDe10DiasMenosDe20() {

		LocalDate fechaCancelacion = LocalDate.of(2024, 11, 1);
		LocalDate fechaInicio = LocalDate.of(2024, 11, 15);
		LocalDate fechaFin = LocalDate.of(2024, 11, 27);
		Reserva reserva = mock(Reserva.class);
		when(reserva.getFechaInicio()).thenReturn(fechaInicio);
		when(reserva.getFechaFin()).thenReturn(fechaFin);
		when(publicacion.precioAlquiler(fechaInicio, fechaFin)).thenReturn(650d);

		double retencion = cancelacionIntermedia.calcularRetencion(fechaCancelacion, reserva, publicacion);
		
		
		assertEquals(325.0, retencion);
	}
	

}
