package busquedaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Inmueble;
import alquiler.Publicacion;
import busqueda.BusquedaCantHuespedes;
import busqueda.BusquedaCompuesta;
import busqueda.BusquedaRangoFechas;

class BusquedaCompuestaTest {
	private BusquedaCompuesta busquedaCompuesta;
	private Publicacion publicacion1;
	private Publicacion publicacion2;
	private Publicacion publicacion3;
	private Inmueble inmuebleEnQuilmes;
	private Inmueble inmuebleEnWilde;
	
	@BeforeEach
	void setUp() {
		String ciudad = "Quilmes";
		LocalDate fechaDesde = LocalDate.of(2024,11,1);
		LocalDate fechaHasta = LocalDate.of(2024,11,7);
		
		this.busquedaCompuesta = new BusquedaCompuesta(ciudad, fechaDesde, fechaHasta);
		
		this.inmuebleEnQuilmes = mock(Inmueble.class);
		when(inmuebleEnQuilmes.estaUbicadoEn("Quilmes")).thenReturn(true);
		when(inmuebleEnQuilmes.permiteCantHuespedes(4)).thenReturn(true);
		this.inmuebleEnWilde = mock(Inmueble.class);
		when(inmuebleEnWilde.estaUbicadoEn("Quilmes")).thenReturn(false);
		when(inmuebleEnWilde.permiteCantHuespedes(4)).thenReturn(true);

		this.publicacion1 = mock(Publicacion.class);
		when(publicacion1.getInmueble()).thenReturn(inmuebleEnQuilmes);
		when(publicacion1.estaDisponible(fechaDesde , fechaHasta)).thenReturn(true);
		
		this.publicacion2 = mock(Publicacion.class);
		when(publicacion2.getInmueble()).thenReturn(inmuebleEnQuilmes);
		when(publicacion2.estaDisponible(fechaDesde , fechaHasta)).thenReturn(false);
		
		this.publicacion3 = mock(Publicacion.class);
		when(publicacion3.getInmueble()).thenReturn(inmuebleEnWilde);
		when(publicacion3.estaDisponible(fechaDesde , fechaHasta)).thenReturn(false);
		
	}

	@Test
	void testFiltrarPorBusquedaCompuesta() {
		List<Publicacion> listaDePublicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);
		
		List<Publicacion> busquedasFiltradas = busquedaCompuesta.filtrar(listaDePublicaciones);
		
		assertEquals(busquedaCompuesta.getCriterios().size() , 2);
		assertTrue(busquedasFiltradas.contains(publicacion1));
		assertFalse(busquedasFiltradas.contains(publicacion2));
		assertFalse(busquedasFiltradas.contains(publicacion3));
	}
	
	@Test
	void testRemoverCriterio() {
		BusquedaCantHuespedes criterioExtra = mock(BusquedaCantHuespedes.class);
		busquedaCompuesta.agregarCriterio(criterioExtra);
		
		busquedaCompuesta.eliminarCriterio(criterioExtra);
		
		assertFalse(busquedaCompuesta.getCriterios().contains(criterioExtra));
	}
	
	@Test
	void testFiltrarConCriteriosAdicionales() {
		busquedaCompuesta.agregarCriterio(new BusquedaCantHuespedes(4));
		
		List<Publicacion> listaDePublicaciones = Arrays.asList(publicacion1, publicacion2, publicacion3);
		List<Publicacion> busquedasFiltradas = busquedaCompuesta.filtrar(listaDePublicaciones);
		
		assertEquals(busquedaCompuesta.getCriterios().size() , 3);
		assertTrue(busquedasFiltradas.contains(publicacion1));
		assertFalse(busquedasFiltradas.contains(publicacion2));
		assertFalse(busquedasFiltradas.contains(publicacion3));
	}
}
