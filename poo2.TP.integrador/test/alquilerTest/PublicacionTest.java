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
import usuario.PropietarioClass;

class PublicacionTest {
	private Publicacion publicacion;
	private PropietarioClass propietario;
	private Inmueble inmueble;
	private List<MetodoPago> formasDePago;
	private Reserva reservaActual;
	private Reserva reservaCondicional;

	@BeforeEach
	void setUp() {
		propietario = mock(PropietarioClass.class);
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
		publicacion.cambiarPrecio(150d);
		
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
	void testObtenerAprobacionDelPropietario() {
		Reserva reserva = mock(Reserva.class);
		
		publicacion.obtenerAprobacionDelPropietario(reserva);
		
		verify(propietario).aprobarReserva(publicacion, reserva);
	}
	
	@Test
	void testEstaDisponibleParaReservar() {
		assertTrue(publicacion.estaDisponible(LocalDate.of(2024, 2, 14), LocalDate.of(2024, 2, 17)));
	}
	
	@Test
	void testProcesarUnaReservaConDisponibilidad() {
		publicacion.procesarReserva(reservaActual);
		List<Reserva> reservasActuales = publicacion.getReservasActuales();
		
		assertTrue(reservasActuales.contains(reservaActual));
	}
	
	@Test
	void testProcesarUnaReservaSinDisponibilidad() {
		publicacion.procesarReserva(reservaActual);
				
		publicacion.procesarReserva(reservaCondicional);
		Queue<Reserva> listaDeEspera = publicacion.getListaDeEspera();
		
		assertTrue(listaDeEspera.contains(reservaCondicional));
	}
	
	@Test
	void testCancelarReserva() {
		publicacion.procesarReserva(reservaActual);
		
		publicacion.cancelarReserva(reservaActual); // cuando se cancela, se quita de las reservas actuales
		List<Reserva> reservasActuales = publicacion.getReservasActuales();
		
		assertFalse(reservasActuales.contains(reservaActual));
	}
	
	@Test
	void testProcesarUnaReservaEnEsperaLuegoDeQueSeCancelaraOtra() {
		publicacion.procesarReserva(reservaActual);
		publicacion.procesarReserva(reservaCondicional);
						
		publicacion.cancelarReserva(reservaActual); // la reserva condicional va a tomar el lugar de la reserva actual
		Queue<Reserva> listaDeEspera = publicacion.getListaDeEspera();
		List<Reserva> reservasActuales = publicacion.getReservasActuales();
		
		assertFalse(listaDeEspera.contains(reservaCondicional));
		assertTrue(reservasActuales.contains(reservaCondicional));
	}
	
	@Test
	void testFinalizarReserva() {
		publicacion.procesarReserva(reservaActual);
				
		publicacion.finalizarReserva(reservaActual);
		List<Reserva> reservasActuales = publicacion.getReservasActuales();
		
		assertFalse(reservasActuales.contains(reservaActual));
	}
	
	@Test
	void testFinalizarReservaLimpiandoReservasQueEstabanEnListaDeEspera() {
		publicacion.procesarReserva(reservaActual);
		publicacion.procesarReserva(reservaCondicional);
		
		publicacion.finalizarReserva(reservaActual);
		Queue<Reserva> listaDeEspera = publicacion.getListaDeEspera();
		
		assertFalse(listaDeEspera.contains(reservaCondicional));
	}
	
	@Test
	void testExcepcionPorCalcularRetencionPorCancelacion() {
		publicacion.procesarReserva(reservaActual);
		when(reservaActual.estaCancelada()).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> { publicacion.retencionPorCancelacion(reservaActual); });
	}
	
	@Test
	void testCalcularRetencionPorCancelacion() {
		PoliticaCancelacion cancelacion = mock(PoliticaCancelacion.class);
		publicacion.setPoliticaCancelacion(cancelacion);
		publicacion.procesarReserva(reservaActual);
		when(reservaActual.estaCancelada()).thenReturn(true);
		
		publicacion.retencionPorCancelacion(reservaActual);
		
		verify(cancelacion).calcularRetencion(LocalDate.now(), reservaActual, publicacion);		
	}
	
	@Test
	void testNoTieneReservas() {
		assertFalse(publicacion.tieneReservas());
	}
	
	@Test
	void testTieneReservas() {	
		publicacion.procesarReserva(reservaActual);
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
		
		verify(suscriptor).cambioDePrecio(publicacion, publicacion.getPrecioBase());
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
