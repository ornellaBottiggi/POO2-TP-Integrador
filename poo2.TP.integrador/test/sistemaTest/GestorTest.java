package sistemaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Reserva;
import enums.Entidad;
import sistema.Calificacion;
import sistema.GestorCalificaciones;
import sistema.SitioWebSAT;

class GestorTest {
	private GestorCalificaciones gestor;
	private SitioWebSAT sitioWeb;
	
	@BeforeEach
	void setUp() {
		sitioWeb = mock(SitioWebSAT.class);
		this.gestor = new GestorCalificaciones(sitioWeb);
	}
	
	@Test
	void testValidarCalificacion() {
		when(sitioWeb.esCategoriaValida("limpieza", Entidad.INMUEBLE)).thenReturn(true);
		Reserva reserva = mock(Reserva.class);
		when(reserva.puedeCalificarse()).thenReturn(true);
		Calificacion calificacion = mock(Calificacion.class);
		when(calificacion.getReserva()).thenReturn(reserva);
		when(calificacion.getCategoria()).thenReturn("limpieza");
		
		gestor.validarCalificacion(calificacion, Entidad.INMUEBLE);
		
		assertTrue(gestor.cumplenRequisitos(reserva, "limpieza", Entidad.INMUEBLE));
	}
	
	@Test 
	void testValidarCalificacionConExcepcion() {
		when(sitioWeb.esCategoriaValida("limpieza", Entidad.INMUEBLE)).thenReturn(false);
		Reserva reserva = mock(Reserva.class);
		when(reserva.puedeCalificarse()).thenReturn(false);
		Calificacion calificacion = mock(Calificacion.class);
		when(calificacion.getReserva()).thenReturn(reserva);
		when(calificacion.getCategoria()).thenReturn("limpieza");
		
		assertThrows(RuntimeException.class, () -> { gestor.validarCalificacion(calificacion, Entidad.INMUEBLE); });
	}
	
	@Test
	void testCalcularPromedioGeneral() {
		Calificacion calificacion1 = mock(Calificacion.class);
		when(calificacion1.getPuntaje()).thenReturn(2);
		Calificacion calificacion2 = mock(Calificacion.class);
		when(calificacion2.getPuntaje()).thenReturn(2);
		
		List<Calificacion> calificaciones = Arrays.asList(calificacion1, calificacion2); 
		
		assertEquals(gestor.calcularPromedioGeneral(calificaciones), 2);
	}
	
	@Test
	void testCalcularPromedioCategoria() {
		Calificacion calificacion2 = mock(Calificacion.class);
		when(calificacion2.getPuntaje()).thenReturn(2);
		when(calificacion2.getCategoria()).thenReturn("Limpieza");
		when(calificacion2.esDeCategoria("Limpieza")).thenReturn(true);
		
		Calificacion calificacion1 = mock(Calificacion.class);
		when(calificacion1.getPuntaje()).thenReturn(4);
		when(calificacion1.getCategoria()).thenReturn("Limpieza");
		when(calificacion1.esDeCategoria("Limpieza")).thenReturn(true);
		
		List<Calificacion> calificaciones = Arrays.asList(calificacion1, calificacion2); 
		
		assertEquals(3.0 , gestor.calcularPromedioDeCategoria("Limpieza", calificaciones));
	}
	
	@Test 
	void testValidarCategoria() {
		when(sitioWeb.esCategoriaValida("Limpieza", Entidad.INMUEBLE)).thenReturn(true);
		gestor.validarCategoria("Limpieza", Entidad.INMUEBLE);
		
		verify(sitioWeb).esCategoriaValida("Limpieza", Entidad.INMUEBLE);
	}
	
	@Test 
	void testValidarCategoriaExcepcion() {
		when(sitioWeb.esCategoriaValida("Limpieza", Entidad.INMUEBLE)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> { gestor.validarCategoria("Limpieza", Entidad.INMUEBLE); });
	}
	
}