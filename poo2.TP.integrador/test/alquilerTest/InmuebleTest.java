package alquilerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import alquiler.Reserva;
import enums.Entidad;
import enums.Servicio;
import sistema.Calificacion;
import sistema.GestorCalificaciones;


class InmuebleTest {
	private Inmueble inmueble;
	private GestorCalificaciones gestorCalificaciones;
	
	@BeforeEach
	void setUp() {
		gestorCalificaciones = mock(GestorCalificaciones.class);
		inmueble = new Inmueble("Departamento", 100d, "Argentina", "Quilmes", "Direccion 123", 3, gestorCalificaciones);
	}
		
	@Test
	void testAgregarServicio() {
		Servicio wifi = mock(Servicio.class);
		
		inmueble.agregarServicio(wifi);
		
		assertTrue(inmueble.getServicios().contains(wifi));
	}
	
	@Test
	void testAgregarFoto() {
		String foto = "foto1.png";
		
		inmueble.agregarFoto(foto);
		
		assertEquals(1, inmueble.cantidadDeFotos());
	}
	
	@Test
	void testExcepcionPorAgregarMasFotosDeLasPosibles() {
		String foto1 = "foto1.png";
		String foto2 = "foto2.png";
		String foto3 = "foto3.png";
		String foto4 = "foto4.png";
		String foto5 = "foto5.png";
		String foto6 = "foto6.png";
		inmueble.agregarFoto(foto1);
		inmueble.agregarFoto(foto2);
		inmueble.agregarFoto(foto3);
		inmueble.agregarFoto(foto4);
		inmueble.agregarFoto(foto5);
		
		assertThrows(RuntimeException.class, () -> { inmueble.agregarFoto(foto6); });
	}
	
	@Test
	void testFueAlquilado() {
		assertFalse(inmueble.fueAlquilado());
	}
	
	@Test
	void testIncrementarVecesAlquilado() {
		inmueble.incrementarVecesAlquilado();
		
		assertTrue(inmueble.fueAlquilado());
	}
	
	@Test
	void testEstaUbicadoEnUnaCiudad() {
		assertTrue(inmueble.estaUbicadoEn("Quilmes"));
		assertFalse(inmueble.estaUbicadoEn("Avellaneda"));
	}
	
	@Test
	void testPermiteUnaCiertaCantidadDeHuespedes() {
		assertTrue(inmueble.permiteCantHuespedes(3));
		assertFalse(inmueble.permiteCantHuespedes(5));
	}
	
	@Test
	void testGetters() {
		assertEquals("Departamento", inmueble.getTipoInmueble());
		assertEquals(100d, inmueble.getSuperficie());
		assertEquals("Argentina", inmueble.getPais());
		assertEquals("Direccion 123", inmueble.getDireccion());
	}
	
	@Test
	void testAgregarCalificacion() {
		Calificacion calificacion = mock(Calificacion.class);
		Entidad inmuebleMock = mock(Entidad.class);
		doNothing().when(gestorCalificaciones).validarCalificacion(calificacion, inmuebleMock);
		
		inmueble.agregarCalificacion(calificacion);
		
		assertTrue(inmueble.getCalificaciones().contains(calificacion));
	}
	
	@Test 
	void testCalcularPromedio() {
		inmueble.calcularPromedio();
		
		verify(gestorCalificaciones).calcularPromedioGeneral(inmueble.getCalificaciones());
	}
	
	@Test 
	void testCalcularPromedioParaCategoria() {
		inmueble.calcularPromedioParaCategoria("limpieza");
		
		verify(gestorCalificaciones).validarCategoria("limpieza", Entidad.INMUEBLE);
		verify(gestorCalificaciones).calcularPromedioDeCategoria("limpieza", inmueble.getCalificaciones());
	}
}
