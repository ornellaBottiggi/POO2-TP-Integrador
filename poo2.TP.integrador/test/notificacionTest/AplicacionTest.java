package notificacionTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import alquiler.Publicacion;
import alquiler.Reserva;
import notificacion.AplicacionMobile;
import notificacion.PopUpWindow;

class AplicacionTest {
	private AplicacionMobile aplicacion;
	private PopUpWindow popUp;
	
	@BeforeEach
	void setUp() {
		this.popUp = mock(PopUpWindow.class);
		this.aplicacion = new AplicacionMobile(popUp);
	}

	@Test
	void testCambioDePrecio() {
		Publicacion publicacion = mock(Publicacion.class);
		PrintStream print = mock(PrintStream.class);
		PrintStream original = System.out;
		System.setOut(print);
		
		aplicacion.cambioDePrecio(publicacion);
		
		verify(print).println("no está interesado en este evento");
		
		System.setOut(original);
		
	}
	
	@Test
	void testCancelacionReserva() {
		Reserva reserva = mock(Reserva.class);
		Inmueble inmueble = mock(Inmueble.class);
		when(reserva.getInmueble()).thenReturn(inmueble);
		when(inmueble.getTipoInmueble()).thenReturn("habitacion");
		
		aplicacion.cancelacionReserva(reserva);
		String mensaje = "El/la habitacion que te interesa se ha liberado! Corre a reservarlo!";
				
		verify(popUp).popUp(mensaje, "red", 12);
	}
	
	@Test
	void testReservaInmueble() {
		Reserva reserva = mock(Reserva.class);
		PrintStream print = mock(PrintStream.class);
		PrintStream original = System.out;
		System.setOut(print);
		
		aplicacion.reservaInmueble(reserva);
		
		verify(print).println("no está interesado en este evento");
		
		System.setOut(original);
	}
	

}
