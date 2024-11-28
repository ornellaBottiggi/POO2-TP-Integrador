package usuarioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import alquiler.Publicacion;
import alquiler.Reserva;
import enums.MetodoPago;
import sistema.Calificacion;
import sistema.SitioWebSAT;
import usuario.Calificable;
import usuario.InquilinoClass;

class InquilinoTest {
	 private InquilinoClass inquilino;
	 	
	@BeforeEach
	void setUp() {
		this.inquilino = new InquilinoClass("Raul", "raul@gmail.com", "1165143349", LocalDate.now());
	}
	
	@Test
	void testCalcularDiasDesdeRegistro() {
		
		assertEquals(0, inquilino.calcularDiasDesdeRegistro());
	}
	
	
	
	@Test
	void testCalificar() {
		SitioWebSAT sitioWeb = mock(SitioWebSAT.class);
		Reserva reserva = mock(Reserva.class);
		when(reserva.puedeCalificarse()).thenReturn(true);
		Calificable propietario = mock(Calificable.class);
		
		inquilino.calificar(sitioWeb, reserva, propietario, "Comunicacion", 0,"");			
		
		verify(sitioWeb).registrarCalificacion(any(Calificable.class), any(Calificacion.class));
	}
	
	@Test
	void testExcepcionCalificar() {
		SitioWebSAT sitioWeb = mock(SitioWebSAT.class);
		Reserva reserva = mock(Reserva.class);
		when(reserva.puedeCalificarse()).thenReturn(false);
		Calificable propietario = mock(Calificable.class);
		
		assertThrows(RuntimeException.class, () -> { inquilino.calificar(sitioWeb, reserva, propietario, "Comunicacion", 0, ""); });
	}
	
	@Test 
	void testRealizarReserva() {
		Publicacion publicacion = mock(Publicacion.class);
		when(publicacion.getInmueble()).thenReturn(mock(Inmueble.class));
		MetodoPago tarjetaCredito = mock(MetodoPago.class);
		
		inquilino.realizarReserva(publicacion, LocalDate.of(2025, 1, 10) ,LocalDate.of(2025, 1, 20), tarjetaCredito);
		
		verify(publicacion).obtenerAprobacionDelPropietario(any(Reserva.class));
	}
	
	@Test
	void testAgregarHistorial() {
		Reserva reserva = mock(Reserva.class);
		
		inquilino.agregarAHistorial(reserva);
		
		assertTrue(inquilino.obtenerReservas().contains(reserva));
	}
	
	@Test
	void testObtenerReservasEnCiudad() {
		Reserva reserva1 = mock(Reserva.class);
		when(reserva1.estaEnCiudad("Quilmes")).thenReturn(false);
		Reserva reserva2 = mock(Reserva.class);
		when(reserva2.estaEnCiudad("Quilmes")).thenReturn(true);
		
		inquilino.agregarAHistorial(reserva1);
		inquilino.agregarAHistorial(reserva2);
		
		List<Reserva> reservas = inquilino.obtenerReservasEnCiudad("Quilmes");
				
		assertTrue(reservas.contains(reserva2));
	}
	
	@Test
	void testObtenerCiudadesConReserva() {
		Reserva reserva = mock(Reserva.class);
		when(reserva.getInmueble()).thenReturn(mock(Inmueble.class));
		when(reserva.getInmueble().getCiudad()).thenReturn("Quilmes");
		
		
		inquilino.agregarAHistorial(reserva);
		
		Set<String> ciudades = inquilino.obtenerCiudadesConReserva();
		
		assertTrue(ciudades.contains("Quilmes"));
	}
	
	@Test
	void testObtenerReservasFuturas() {
		Reserva reserva1 = mock(Reserva.class);
		when(reserva1.esPosteriorA(LocalDate.now())).thenReturn(false);
		Reserva reserva2 = mock(Reserva.class);
		when(reserva2.esPosteriorA(LocalDate.now())).thenReturn(true);
		
		inquilino.agregarAHistorial(reserva1);
		inquilino.agregarAHistorial(reserva2);
		
		List<Reserva> reservasFuturas = inquilino.obtenerReservasFuturas();
		
		assertTrue(reservasFuturas.contains(reserva2));
	}
	
	@Test
	void testEsInquilino() {
		
		assertTrue(inquilino.esInquilino());
	}
	
	@Test
	void testCantidadReservas() {
		
		assertEquals(0,inquilino.cantidadReservas());
	}
	
	
}
