package busquedaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Publicacion;
import busqueda.BusquedaPrecioMaximo;

class BusquedaPrecioMaximoTest {
	private BusquedaPrecioMaximo busquedaPrecioMaximo;
	private Publicacion publicacion1;
	private Publicacion publicacion2;
	private Publicacion publicacion3;
	
	@BeforeEach
	void setUp() {
	this.busquedaPrecioMaximo = new BusquedaPrecioMaximo(100);
	
	this.publicacion1 = mock(Publicacion.class);
	when(publicacion1.getPrecioBase()).thenReturn(150d);
	this.publicacion2 = mock(Publicacion.class);
	when(publicacion2.getPrecioBase()).thenReturn(200d);
	this.publicacion3 = mock(Publicacion.class);
	when(publicacion3.getPrecioBase()).thenReturn(50d);

	}

	@Test
	void testFiltrarPorPrecioMaximo() {
		List<Publicacion> listaDePublicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);
		
		List<Publicacion> busquedasFiltradas = busquedaPrecioMaximo.filtrar(listaDePublicaciones);
		
		assertTrue(busquedasFiltradas.contains(publicacion3));
		assertFalse(busquedasFiltradas.contains(publicacion1));
		assertFalse(busquedasFiltradas.contains(publicacion2));
	}

}
