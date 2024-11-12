package alquilerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.PrecioTemporada;

class PrecioTemporadaTest {
	private PrecioTemporada precioTemporada;

	@BeforeEach
	void setUp() {
		precioTemporada = new PrecioTemporada("Carnaval", LocalDate.of(2024, 2, 14), LocalDate.of(2024, 2, 17), 50d);
	}

	@Test
	void testUnaFechaPerteneceAlPeriodoDelPrecio() {
		LocalDate fechaBuscada = LocalDate.of(2024, 2, 15);
		LocalDate mismaFechaInicio = LocalDate.of(2024, 2, 14);
		LocalDate mismaFechaFin = LocalDate.of(2024, 2, 17);
		
		assertTrue(precioTemporada.pertenece(fechaBuscada));
		assertTrue(precioTemporada.pertenece(mismaFechaInicio));
		assertTrue(precioTemporada.pertenece(mismaFechaFin));
		
	}
	
	@Test
	void testUnaFechaNoPerteneceAlPeriodoDelPrecio() {
		LocalDate fechaBuscada = LocalDate.of(2024, 2, 10);
		LocalDate fechaPosterior = LocalDate.of(2024, 2, 19);
	
		assertFalse(precioTemporada.pertenece(fechaBuscada));
		assertFalse(precioTemporada.pertenece(fechaPosterior));
	}
	
	@Test
	void testGetters() {
		assertEquals("Carnaval", precioTemporada.getTemporada());
		assertEquals(50d, precioTemporada.getPrecio());
	}

}
