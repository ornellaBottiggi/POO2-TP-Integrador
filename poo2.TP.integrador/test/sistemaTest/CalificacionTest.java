package sistemaTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sistema.Calificacion;
import usuario.Calificable;

class CalificacionTest {
	private Calificacion calificacion;
	private Calificable departamento;
	
	@BeforeEach
	void setUp() {
		this.calificacion = new Calificacion(departamento, "Limpieza", 3, "muy buen departamento");
		
	}

	@Test
	void testGetEntidadPuntuada() {
		
		assertEquals(calificacion.getEntidadPuntuada(), departamento);
	}

	@Test
	void testGetCategoria() {
		
		assertEquals(calificacion.getCategoria(), "Limpieza");
	}
	
	@Test
	void testGetPuntaje() {
		
		assertEquals(calificacion.getPuntaje(), 3);
	}
	
	@Test
	void testGetcomentario() {
		
		assertEquals(calificacion.getComentario(), "muy buen departamento");
	}
	
	@Test
	void testEsDeCategoria() {
		
		assertTrue(calificacion.esDeCategoria("Limpieza"));
	}
	
}
