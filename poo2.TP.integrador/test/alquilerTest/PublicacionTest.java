package alquilerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import alquiler.PrecioTemporada;
import alquiler.Publicacion;
import alquiler.Reserva;
import cancelacion.CancelacionGratuita;
import cancelacion.PoliticaCancelacion;
import enums.MetodoPago;
import notificacion.Suscriptor;
import usuario.Propietario;

class PublicacionTest {
	private Publicacion publicacion;
	private Propietario propietario;
	private Inmueble inmueble;
	private List<MetodoPago> formasDePago;
	private Reserva reservaActual;
	private Reserva reservaCondicional;

	@BeforeEach
	void setUp() {
		propietario = mock(Propietario.class);
		inmueble = mock(Inmueble.class);
		formasDePago = Arrays.asList(mock(MetodoPago.class));
		
		publicacion = new Publicacion(propietario, inmueble, LocalTime.of(10,00), LocalTime.of(20,00), formasDePago, 100d);
		
		LocalDate fechaDesde = LocalDate.of(2024, 2, 10);
		LocalDate fechaHasta = LocalDate.of(2024, 2, 12);
		
		reservaActual = mock(Reserva.class);
		when(reservaActual.getFechaInicio()).thenReturn(fechaDesde);
		when(reservaActual.getFechaFin()).thenReturn(fechaHasta);
		when(reservaActual.seSuperponeCon(fechaDesde, fechaHasta)).thenReturn(true);
		
		reservaCondicional = mock(Reserva.class);
		when(reservaCondicional.getFechaInicio()).thenReturn(fechaDesde);
		when(reservaCondicional.getFechaFin()).thenReturn(fechaHasta);
		when(reservaCondicional.seSuperponeCon(fechaDesde, fechaHasta)).thenReturn(true);
	}

	@Test
	void testAgregarPrecioTemporada() {
		PrecioTemporada precioTemporada = mock(PrecioTemporada.class);
		
		publicacion.agregarPrecioTemporada(precioTemporada);
		List<PrecioTemporada> precios = publicacion.getPreciosTemporada();
		
		assertTrue(precios.contains(precioTemporada));
	}

	@Test
	void testCalcularElPrecioTotalDeAlquilerConElPrecioBasePorDia() {
		PrecioTemporada precioTemporada = mock(PrecioTemporada.class);
		when(precioTemporada.pertenece(any(LocalDate.class))).thenReturn(false);
		publicacion.agregarPrecioTemporada(precioTemporada);
		
		LocalDate fechaDesde = LocalDate.of(2024, 2, 10);
		LocalDate fechaHasta = LocalDate.of(2024, 2, 12);
		
		double precioAlquiler = publicacion.precioAlquiler(fechaDesde, fechaHasta); 
		
		assertEquals(300d, precioAlquiler);
	}
	
	@Test
	void testCalcularElPrecioTotalDeAlquilerConPrecioDeTemporadaPorDia() {
		LocalDate fechaEspecial = LocalDate.of(2024, 2, 11);
		
		PrecioTemporada precioTemporada = mock(PrecioTemporada.class);
		when(precioTemporada.pertenece(fechaEspecial)).thenReturn(true);
		when(precioTemporada.getPrecio()).thenReturn(50d);
		publicacion.agregarPrecioTemporada(precioTemporada);
		
		LocalDate fechaDesde = LocalDate.of(2024, 2, 10);
		LocalDate fechaHasta = LocalDate.of(2024, 2, 12);
		
		double precioAlquiler = publicacion.precioAlquiler(fechaDesde, fechaHasta); 
		
		assertEquals(250d, precioAlquiler);
	}
	
	@Test
	void testSubirElPrecioBase() {
		double precioViejo = publicacion.getPrecioBase();
		
		publicacion.cambiarPrecio(150d);
		double precioNuevo = publicacion.getPrecioBase();
		
		assertTrue(precioNuevo > precioViejo);
		assertEquals(150d, publicacion.getPrecioBase());
	}
	
	@Test
	void testBajarElPrecioBase() {
		double precioViejo = publicacion.getPrecioBase();
		
		publicacion.cambiarPrecio(50d);
		double precioNuevo = publicacion.getPrecioBase();
		
		assertTrue(precioNuevo < precioViejo);
	}
	
	@Test
	void testRechazarReserva() {
		Reserva reserva = mock(Reserva.class);
		
		publicacion.rechazarReserva(reserva);
		
		verify(reserva).registrarRechazo();
	}
	
	@Test
	void testEstaDisponibleParaReservar() {
		assertTrue(publicacion.estaDisponible(LocalDate.of(2024, 2, 14), LocalDate.of(2024, 2, 17)));
	}
	
	@Test
	void testAceptarUnaReservaConDisponibilidad() {
		publicacion.aceptarReserva(reservaActual);
		List<Reserva> reservasActuales = publicacion.getReservasActuales();
		
		assertTrue(reservasActuales.contains(reservaActual));
	}
	
	@Test
	void testAceptarUnaReservaSinDisponibilidad() {
		publicacion.aceptarReserva(reservaActual);
				
		publicacion.aceptarReserva(reservaCondicional);
		Queue<Reserva> listaDeEspera = publicacion.getListaDeEspera();
		
		assertTrue(listaDeEspera.contains(reservaCondicional));
	}
	
	@Test
	void testCancelarReserva() {
		PoliticaCancelacion cancelacion = mock(PoliticaCancelacion.class);
		when(cancelacion.calcularRetencion(LocalDate.now(), reservaActual, publicacion)).thenReturn(0.0);
		publicacion.setPoliticaCancelacion(cancelacion);
		publicacion.aceptarReserva(reservaActual);
		
		double retencion = publicacion.cancelarReserva(reservaActual);
		List<Reserva> reservasActuales = publicacion.getReservasActuales();
		
		assertFalse(reservasActuales.contains(reservaActual)); // cuando se cancela, se quita de las reservas actuales
		assertEquals(retencion, 0.0);
	}
	
	@Test
	void testProcesarUnaReservaEnEsperaLuegoDeQueSeCancelaraOtra() {
		PoliticaCancelacion cancelacion = mock(PoliticaCancelacion.class);
		publicacion.setPoliticaCancelacion(cancelacion);
		publicacion.aceptarReserva(reservaActual);
		publicacion.aceptarReserva(reservaCondicional);
						
		publicacion.cancelarReserva(reservaActual); // la reserva condicional va a tomar el lugar de la reserva actual
		Queue<Reserva> listaDeEspera = publicacion.getListaDeEspera();
		List<Reserva> reservasActuales = publicacion.getReservasActuales();
		
		assertFalse(listaDeEspera.contains(reservaCondicional));
		assertTrue(reservasActuales.contains(reservaCondicional));
	}
	
	@Test
	void testFinalizarReserva() {
		publicacion.aceptarReserva(reservaActual);
				
		publicacion.finalizarReserva(reservaActual);
		List<Reserva> reservasActuales = publicacion.getReservasActuales();
		
		assertFalse(reservasActuales.contains(reservaActual));
	}
	
	@Test
	void testFinalizarReservaLimpiandoReservasQueEstabanEnListaDeEspera() {
		publicacion.aceptarReserva(reservaActual);
		publicacion.aceptarReserva(reservaCondicional);
		
		publicacion.finalizarReserva(reservaActual);
		Queue<Reserva> listaDeEspera = publicacion.getListaDeEspera();
		
		assertFalse(listaDeEspera.contains(reservaCondicional));
	}
	
	@Test
	void testNoTieneReservas() {
		assertFalse(publicacion.tieneReservas());
	}
	
	@Test
	void testTieneReservas() {	
		publicacion.aceptarReserva(reservaActual);
		assertTrue(publicacion.tieneReservas());
	}
	
	@Test
	void testAgregarSuscriptor() {
		Suscriptor suscriptor = mock(Suscriptor.class);
		
		publicacion.agregarSuscriptor(suscriptor);
		List<Suscriptor> suscriptores = publicacion.getSuscriptores();
		
		assertTrue(suscriptores.contains(suscriptor));
	}
	
	@Test
	void testEliminarSuscriptor() {
		Suscriptor suscriptor = mock(Suscriptor.class);
		publicacion.agregarSuscriptor(suscriptor);
		
		publicacion.eliminarSuscriptor(suscriptor);
		List<Suscriptor> suscriptores = publicacion.getSuscriptores();
		
		assertFalse(suscriptores.contains(suscriptor));		
	}
	
	@Test
	void testNotificarBajaDePrecio() {
		Suscriptor suscriptor = mock(Suscriptor.class);
		publicacion.agregarSuscriptor(suscriptor);
		
		publicacion.notificarBajaDePrecio();
		
		verify(suscriptor).cambioDePrecio(publicacion);
	}
	
	@Test
	void testNotificarCancelacionDeReserva() {
		Suscriptor suscriptor = mock(Suscriptor.class);
		publicacion.agregarSuscriptor(suscriptor);
				
		publicacion.notificarCancelacionDeReserva(reservaActual);
		
		verify(suscriptor).cancelacionReserva(reservaActual);
	}
	
	@Test
	void testNotificarReservaDeInmueble() {
		Suscriptor suscriptor = mock(Suscriptor.class);
		publicacion.agregarSuscriptor(suscriptor);
				
		publicacion.notificarReserva(reservaActual);
		
		verify(suscriptor).reservaInmueble(reservaActual);
	}
	
	@Test
	void testGetters() {
		assertEquals(propietario, publicacion.getPropietario());
		assertEquals(inmueble, publicacion.getInmueble());
		assertEquals(LocalTime.of(10,00), publicacion.getCheckIn());
		assertEquals(LocalTime.of(20,00), publicacion.getCheckOut());
		assertEquals(formasDePago, publicacion.getFormasDePago());
	}
	
}
