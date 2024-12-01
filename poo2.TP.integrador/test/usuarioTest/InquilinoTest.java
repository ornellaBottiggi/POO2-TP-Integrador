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
import enums.Entidad;
import enums.MetodoPago;
import sistema.Calificacion;
import sistema.GestorCalificaciones;
import sistema.SitioWebSAT;
import usuario.Inquilino;
import usuario.Usuario;

class InquilinoTest {
	 private Inquilino inquilino;
	 private GestorCalificaciones gestor;
	 
	@BeforeEach
	void setUp() {
		gestor = mock(GestorCalificaciones.class);
		this.inquilino = new Usuario("Raul", "raul@gmail.com", "1165143349", LocalDate.now(), gestor);
	}
	
	@Test
	void testCalcularDiasDesdeRegistro() {
		assertEquals(0, inquilino.calcularDiasDesdeRegistro());
	}
	
	@Test
	void testAgregarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		inquilino.agregarReserva(reserva);
		
		assertTrue(inquilino.getReservas().contains(reserva));
	}
	
	@Test
	void testObtenerReservasEnCiudad() {
		Reserva reserva1 = mock(Reserva.class);
		when(reserva1.estaEnCiudad("Quilmes")).thenReturn(false);
		Reserva reserva2 = mock(Reserva.class);
		when(reserva2.estaEnCiudad("Quilmes")).thenReturn(true);
		
		inquilino.agregarReserva(reserva1);
		inquilino.agregarReserva(reserva2);
		
		List<Reserva> reservas = inquilino.obtenerReservasEnCiudad("Quilmes");
				
		assertTrue(reservas.contains(reserva2));
	}
	
	@Test
	void testObtenerCiudadesConReserva() {
		Reserva reserva = mock(Reserva.class);
		when(reserva.getInmueble()).thenReturn(mock(Inmueble.class));
		when(reserva.getInmueble().getCiudad()).thenReturn("Quilmes");
		
		
		inquilino.agregarReserva(reserva);
		
		Set<String> ciudades = inquilino.obtenerCiudadesConReserva();
		
		assertTrue(ciudades.contains("Quilmes"));
	}
	
	@Test
	void testObtenerReservasFuturas() {
		Reserva reserva1 = mock(Reserva.class);
		when(reserva1.esPosteriorA(LocalDate.now())).thenReturn(false);
		Reserva reserva2 = mock(Reserva.class);
		when(reserva2.esPosteriorA(LocalDate.now())).thenReturn(true);
		
		inquilino.agregarReserva(reserva1);
		inquilino.agregarReserva(reserva2);
		
		List<Reserva> reservasFuturas = inquilino.obtenerReservasFuturas();
		
		assertTrue(reservasFuturas.contains(reserva2));
	}
	
	@Test
	void testEsInquilino() {
		Reserva reserva = mock(Reserva.class);
		
		inquilino.agregarReserva(reserva);
		
		assertTrue(inquilino.tieneReservas());
	}
	
	@Test
	void testCantidadReservas() {	
		assertEquals(0,inquilino.cantidadReservas());
	}
	
	@Test 
	void testAgregarCalificacion() {
		Calificacion calificacion = mock(Calificacion.class);
		doNothing().when(gestor).validarCalificacion(calificacion, Entidad.INQUILINO);
		
		inquilino.agregarCalificacionInquilino(calificacion);
		
		assertTrue(inquilino.getCalificacionesInquilino().contains(calificacion));
	}
	
	@Test
	void testCalcularPromedioInquilino() {
		inquilino.calcularPromedioInquilino();
				
		verify(gestor).calcularPromedioGeneral(inquilino.getCalificacionesInquilino());
	}
	
	@Test 
	void testCalcularPromedioCategoriaInquilino() {
		inquilino.calcularPromedioCategoriaInquilino("amabilidad");
	
		verify(gestor).validarCategoria("amabilidad", Entidad.INQUILINO);
		verify(gestor).calcularPromedioDeCategoria("amabilidad",inquilino.getCalificacionesInquilino());
	}
}
