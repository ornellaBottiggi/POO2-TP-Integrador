package busquedaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Publicacion;
import busqueda.BusquedaPrecioMinimo;

class BusquedaPrecioMinimoTest {

		private BusquedaPrecioMinimo busquedaPrecioMinimo;
		private Publicacion publicacion1;
		private Publicacion publicacion2;
		private Publicacion publicacion3;
		
		@BeforeEach
		void setUp() {
		this.busquedaPrecioMinimo = new BusquedaPrecioMinimo(100);
		
		this.publicacion1 = mock(Publicacion.class);
		when(publicacion1.getPrecioBase()).thenReturn(150d);
		this.publicacion2 = mock(Publicacion.class);
		when(publicacion2.getPrecioBase()).thenReturn(200d);
		this.publicacion3 = mock(Publicacion.class);
		when(publicacion3.getPrecioBase()).thenReturn(50d);

		}

	@Test
	void testFiltrarPorPrecioMinimo() {
		List<Publicacion> listaDePublicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);
		
		List<Publicacion> busquedasFiltradas = busquedaPrecioMinimo.filtrar(listaDePublicaciones);
		
		assertTrue(busquedasFiltradas.contains(publicacion1));
		assertTrue(busquedasFiltradas.contains(publicacion2));
		assertFalse(busquedasFiltradas.contains(publicacion3));
	}
}
