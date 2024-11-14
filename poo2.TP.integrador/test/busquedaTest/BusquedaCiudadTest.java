package busquedaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import alquiler.Publicacion;
import busqueda.BusquedaCiudad;

class BusquedaCiudadTest {
	private BusquedaCiudad busquedaCiudad;
	private Publicacion publicacion1;
	private Publicacion publicacion2;
	private Publicacion publicacion3;
	private Inmueble inmuebleEnQuilmes;
	private Inmueble inmuebleEnWilde;
	
	@BeforeEach
	void setUp() {
		this.busquedaCiudad = new BusquedaCiudad("Quilmes");
		
		this.inmuebleEnQuilmes = mock(Inmueble.class);
		when(inmuebleEnQuilmes.estaUbicadoEn("Quilmes")).thenReturn(true);
		this.inmuebleEnWilde = mock(Inmueble.class);
		when(inmuebleEnWilde.estaUbicadoEn("Quilmes")).thenReturn(false);
		
		this.publicacion1 = mock(Publicacion.class);
		when(publicacion1.getInmueble()).thenReturn(inmuebleEnQuilmes);
		this.publicacion2 = mock(Publicacion.class);
		when(publicacion2.getInmueble()).thenReturn(inmuebleEnQuilmes);
		this.publicacion3 = mock(Publicacion.class);
		when(publicacion3.getInmueble()).thenReturn(inmuebleEnWilde);
	}

	@Test
	void testFiltrarPorCiudad() {
		List<Publicacion> listaDePublicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);
		
		List<Publicacion> busquedasFiltradas = busquedaCiudad.filtrar(listaDePublicaciones);
		
		assertTrue(busquedasFiltradas.contains(publicacion1));
		assertTrue(busquedasFiltradas.contains(publicacion2));
		assertFalse(busquedasFiltradas.contains(publicacion3));
	}

}
