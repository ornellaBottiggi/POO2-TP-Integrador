package usuarioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import enums.Entidad;
import sistema.Calificacion;
import sistema.GestorCalificaciones;
import usuario.Propietario;
import usuario.Usuario;

class PropietarioTest {
	private Propietario propietario;
	private GestorCalificaciones gestor;
	
	@BeforeEach
	void setUp() {
		gestor = mock(GestorCalificaciones.class);
		this.propietario = new Usuario("Silvia", "silvia@gmail.com", "1542385478", LocalDate.of(2024, 10, 15), gestor);
	}

	@Test
	void testAgregarInmueble() {
		Inmueble inmueble = mock(Inmueble.class);
		
		propietario.agregarInmueble(inmueble);
		
		assertTrue(propietario.getInmuebles().contains(inmueble));
	}
	
	@Test
	void testInmueblesAlquilados() {
		Inmueble inmueble1 = mock(Inmueble.class);
		when(inmueble1.fueAlquilado()).thenReturn(true);
		Inmueble inmueble2 = mock(Inmueble.class);
		when(inmueble2.fueAlquilado()).thenReturn(false);		
		
		propietario.agregarInmueble(inmueble1);
		propietario.agregarInmueble(inmueble2);
		
		List<Inmueble> inmueblesAlquilados = propietario.inmueblesAlquilados();
		
		assertTrue(inmueblesAlquilados.contains(inmueble1));
	}
	
	@Test
	void testCantidadInmueblesAlquilados() {
		assertEquals(propietario.cantidadInmueblesAlquilados(), 0);
	}

	@Test
	void testNoEsInquilino() {
		
		assertFalse(propietario.tieneReservas());
	}
	
	@Test 
	void testGetters() {
		
		assertEquals("Silvia", propietario.getNombre());
		assertEquals("silvia@gmail.com", propietario.getEmail());
		assertEquals("1542385478", propietario.getTelefono());
		assertEquals(LocalDate.of(2024, 10, 15), propietario.getFechaRegistro());
	}
	
	@Test 
	void testAgregarCalificacion() {
		Calificacion calificacion = mock(Calificacion.class);
		doNothing().when(gestor).validarCalificacion(calificacion, Entidad.PROPIETARIO);
		
		propietario.agregarCalificacionPropietario(calificacion);
		
		assertTrue(propietario.getCalificacionesPropietario().contains(calificacion));
	}
	
	@Test
	void testCalcularPromedioPropietario() {
		propietario.calcularPromedioPropietario();
				
		verify(gestor).calcularPromedioGeneral(propietario.getCalificacionesPropietario());
	}
	
	@Test 
	void testCalcularPromedioCategoriaPropietario() {
		propietario.calcularPromedioCategoriaPropietario("comunicacion");
	
		verify(gestor).validarCategoria("comunicacion", Entidad.PROPIETARIO);
		verify(gestor).calcularPromedioDeCategoria("comunicacion",propietario.getCalificacionesPropietario());
	}
	
}
