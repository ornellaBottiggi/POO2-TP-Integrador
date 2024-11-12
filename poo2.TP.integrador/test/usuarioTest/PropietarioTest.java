package usuarioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import alquiler.Publicacion;
import alquiler.Reserva;
import enums.MetodoPago;
import sistema.SitioWebSAT;
import usuario.Propietario;

class PropietarioTest {
	private Propietario propietario;
		
	@BeforeEach
	void setUp() {
		this.propietario = new Propietario("Silvia", "silvia@gmail.com", "1542385478", LocalDate.of(2024, 10, 15));
	}

	@Test
	void testAgregarInmueble() {
		Inmueble inmueble = mock(Inmueble.class);
		
		propietario.agregarInmueble(inmueble);
		
		assertTrue(propietario.getInmuebles().contains(inmueble));
	}
	
	@Test
	void testGenerarPublicacion() {
		SitioWebSAT sitioWeb = mock(SitioWebSAT.class);
		Inmueble inmueble = mock(Inmueble.class);
		List<MetodoPago> metodosPago = Arrays.asList(mock(MetodoPago.class));
		
		propietario.generarPublicacion(sitioWeb, inmueble, LocalTime.of(14,00), LocalTime.of(14, 00), metodosPago, 50d);
		
		verify(sitioWeb).altaDePublicacion(any(Publicacion.class));
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
	void testCantidadDeInmueblesAlquilados() {
		
		assertEquals(propietario.cantidadDeInmueblesAlquilados(), 0);
	}
	
	@Test
	void testAprobarReserva() {
		Publicacion publicacion = mock(Publicacion.class);
		Reserva reserva = mock(Reserva.class);
		
		propietario.aprobarReserva(publicacion, reserva);
		
		verify(publicacion).procesarReserva(any(Reserva.class));
	}
	
	@Test
	void testEsInquilino() {
		
		assertFalse(propietario.esInquilino());
	}
	
	@Test
	void testCantidadReservas() {
		
		assertEquals(0,propietario.cantidadReservas());
	}
	
	@Test 
	void testGetters() {
		
		assertEquals("Silvia", propietario.getNombre());
		assertEquals("silvia@gmail.com", propietario.getEmail());
		assertEquals("1542385478", propietario.getTelefono());
		assertEquals(LocalDate.of(2024, 10, 15), propietario.getFechaRegistro());
	}
	
	
}
