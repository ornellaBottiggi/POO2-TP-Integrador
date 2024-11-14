package busquedaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import alquiler.Publicacion;
import busqueda.BusquedaCantHuespedes;

class BusquedaCantHuespedesTest {
	private BusquedaCantHuespedes busquedaCantHuespedes;
	private Publicacion publicacion1;
	private Publicacion publicacion2;
	private Publicacion publicacion3;
	private Inmueble inmueblePara3;
	private Inmueble inmueblePara6;
	
	@BeforeEach
	void setUp() {
		this.busquedaCantHuespedes = new BusquedaCantHuespedes(6);
		
		this.inmueblePara3 = mock(Inmueble.class);
		when(inmueblePara3.permiteCantHuespedes(6)).thenReturn(false);
		this.inmueblePara6 = mock(Inmueble.class);
		when(inmueblePara6.permiteCantHuespedes(6)).thenReturn(true);
		
		this.publicacion1 = mock(Publicacion.class);
		when(publicacion1.getInmueble()).thenReturn(inmueblePara3);
		this.publicacion2 = mock(Publicacion.class);
		when(publicacion2.getInmueble()).thenReturn(inmueblePara3);
		this.publicacion3 = mock(Publicacion.class);
		when(publicacion3.getInmueble()).thenReturn(inmueblePara6);
	}

	@Test
	void testFiltroPorCantidadDeHuespedes() {
		List<Publicacion> listaDePublicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);
		
		List<Publicacion> busquedasFiltradas = busquedaCantHuespedes.filtrar(listaDePublicaciones);
	
		assertTrue(busquedasFiltradas.contains(publicacion3));
		assertFalse(busquedasFiltradas.contains(publicacion1));
		assertFalse(busquedasFiltradas.contains(publicacion2));

	}
}
