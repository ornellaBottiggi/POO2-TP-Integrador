package notificacionTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import alquiler.Publicacion;
import alquiler.Reserva;
import notificacion.HomePagePublisher;
import notificacion.PortalDeOfertas;

class PortalDeOfertasTest {
	private PortalDeOfertas portalDeOfertas;
	private HomePagePublisher publisher;
	
	@BeforeEach
	void setUp() {
		this.publisher = mock(HomePagePublisher.class);
		this.portalDeOfertas = new PortalDeOfertas(publisher);
		
	}

	@Test
	void testCambioDePrecio() {
		Publicacion publicacion = mock(Publicacion.class);
		Inmueble inmueble = mock(Inmueble.class);
		when(publicacion.getInmueble()).thenReturn(inmueble);
		when(publicacion.getPrecioBase()).thenReturn(50.0);
		when(inmueble.getTipoInmueble()).thenReturn("habitacion");
		
		portalDeOfertas.cambioDePrecio(publicacion);
		String mensaje = "No te pierdas esta oferta: Un inmueble habitacion a tan solo 50.0 pesos.";
				
		verify(publisher).publish(mensaje);
	}
	
	@Test
	void testCancelacionReserva() {
		Reserva reserva = mock(Reserva.class);
		PrintStream print = mock(PrintStream.class);
		PrintStream original = System.out;
		System.setOut(print);
		
		portalDeOfertas.cancelacionReserva(reserva);
		
		verify(print).println("no está interesado en este evento");
		
		System.setOut(original);

	}
	
	@Test
	void testReservaInmueble() {
		Reserva reserva = mock(Reserva.class);
		PrintStream print = mock(PrintStream.class);
		PrintStream original = System.out;
		System.setOut(print);
		
		portalDeOfertas.reservaInmueble(reserva);
		
		verify(print).println("no está interesado en este evento");
		
		System.setOut(original);
	}

}
