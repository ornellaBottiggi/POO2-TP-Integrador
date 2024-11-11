package sistemaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistema.Calificacion;
import sistema.GestorCalificaciones;
import usuario.Calificable;

class GestorTest {
	
	private GestorCalificaciones gestor;
	private Calificable calificable;
	private Calificacion calificacion;
	
	@BeforeEach
	void setUp() {
		this.gestor = new GestorCalificaciones();
		calificable = mock(Calificable.class);
		calificacion = mock(Calificacion.class);
	}

	@Test
	void testAgregarCalificacion() {
		
		gestor.agregarCalificacion(calificable, calificacion);
		
		assertTrue(gestor.getCalificacionesEntidades().containsKey(calificable));
		assertTrue(gestor.getCalificacionesEntidades().containsValue(Arrays.asList(calificacion)));
	}
	
	@Test
	void testObtenerCalificaciones() {
		
		gestor.agregarCalificacion(calificable, calificacion);
		
		assertTrue(gestor.obtenerCalificaciones(calificable).contains(calificacion));
	}
	
	@Test
	void testCalcularPromedioGeneral() {
		when(calificacion.getPuntaje()).thenReturn(2);
		Calificacion calificacion1 = mock(Calificacion.class);
		when(calificacion1.getPuntaje()).thenReturn(2);
		
		gestor.agregarCalificacion(calificable, calificacion);
		gestor.agregarCalificacion(calificable, calificacion1);
		
		assertEquals(gestor.calcularPromedioGeneral(calificable), 2);
	}
	
	@Test
	void testCalcularPromedioCategorias() {
		
		/*los siguientes when corresponden al "calificacion" del setup*/
		when(calificacion.getPuntaje()).thenReturn(2);
		when(calificacion.getCategoria()).thenReturn("Limpieza");
		when(calificacion.esDeCategoria("Limpieza")).thenReturn(true);
		
		Calificacion calificacion1 = mock(Calificacion.class);
		when(calificacion1.getPuntaje()).thenReturn(4);
		when(calificacion1.getCategoria()).thenReturn("Limpieza");
		when(calificacion1.esDeCategoria("Limpieza")).thenReturn(true);
		
		gestor.agregarCalificacion(calificable, calificacion1);
		gestor.agregarCalificacion(calificable, calificacion);
		
		Map<String, Double> resultadoEsperado = new HashMap<String, Double>();
		resultadoEsperado.put("Limpieza", 3d);
		
		assertEquals(resultadoEsperado , gestor.calcularPromedioCategorias(calificable));
	}
	
	

	












}
