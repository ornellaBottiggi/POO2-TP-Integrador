package sistemaTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import alquiler.Reserva;
import sistema.Calificacion;

class CalificacionTest {
	private Calificacion calificacion;
	private Reserva reserva;
	
	@BeforeEach
	void setUp() {
		reserva = mock(Reserva.class);
		this.calificacion = new Calificacion(reserva, "Limpieza", 3, "muy buen departamento");
		
	}

	@Test
	void testGetEntidadPuntuada() {
		
		assertEquals(calificacion.getReserva(), reserva);
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
