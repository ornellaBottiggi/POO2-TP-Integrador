package busquedaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Publicacion;
import busqueda.BusquedaPrecioMinimo;
import busqueda.BusquedaRangoFechas;

class BusquedaRangoFechasTest {
	private BusquedaRangoFechas busquedaRango;
	private Publicacion publicacion1;
	private Publicacion publicacion2;
	private Publicacion publicacion3;
	
	@BeforeEach
	void setUp() {
		
		LocalDate fechaDesde = LocalDate.of(2024,11,1);
		LocalDate fechaHasta = LocalDate.of(2024,11,7);
		this.busquedaRango = new BusquedaRangoFechas(fechaDesde , fechaHasta);
		
		this.publicacion1 = mock(Publicacion.class);
		when(publicacion1.estaDisponible(fechaDesde , fechaHasta)).thenReturn(true);
		this.publicacion2 = mock(Publicacion.class);
		when(publicacion2.estaDisponible(fechaDesde , fechaHasta)).thenReturn(false);
		this.publicacion3 = mock(Publicacion.class);
		when(publicacion3.estaDisponible(fechaDesde , fechaHasta)).thenReturn(false);

	}

	@Test
	void testFiltrarPorRango() {
		List<Publicacion> listaDePublicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);
		
		List<Publicacion> busquedasFiltradas = busquedaRango.filtrar(listaDePublicaciones);
		
		assertTrue(busquedasFiltradas.contains(publicacion1));
		assertFalse(busquedasFiltradas.contains(publicacion2));
		assertFalse(busquedasFiltradas.contains(publicacion3));
	}

}
