package sistemaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import alquiler.Publicacion;
import busqueda.BusquedaCompuesta;
import enums.Servicio;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import sistema.Calificacion;
import sistema.GestorCalificaciones;
import sistema.SitioWebSAT;
import usuario.Calificable;
import usuario.Inquilino;
import usuario.Usuario;

class SitioWebTest {
	
	private SitioWebSAT sitioWeb;
	private GestorCalificaciones gestor;
	
	
	@BeforeEach
	void setUp() {
		this.gestor = mock(GestorCalificaciones.class);
		this.sitioWeb = new SitioWebSAT(gestor);
	}

	
	@Test
	void testRegistrarUsuario() {
		Inquilino inquilino = mock(Inquilino.class);
		
		sitioWeb.registrarUsuario(inquilino);
		
		assertTrue(sitioWeb.getUsuarios().contains(inquilino));
	}
	
	@Test
	void testAltaDePublicacion() {
		Publicacion publicacion = mock(Publicacion.class);
		
		sitioWeb.altaDePublicacion(publicacion);
		
		assertTrue(sitioWeb.getPublicaciones().contains(publicacion));
	}

	@Test
	void testAltaDeCategoria() {
		
		sitioWeb.altaDeCategoria("Limpieza");
		
		assertTrue(sitioWeb.getCategorias().contains("Limpieza"));
	}

	@Test
	void testAltaDeServicio() {
		Servicio servicioWIFI = mock(Servicio.class);
		
		sitioWeb.altaDeServicio(servicioWIFI);
	
		assertTrue(sitioWeb.getServicios().contains(servicioWIFI));
	}

	@Test
	void testAltaDeTipoDeInmueble() {
		
		sitioWeb.altaDeTipoDeInmueble("Departamento");
		
		assertTrue(sitioWeb.getTiposInmuebles().contains("Departamento"));
	}
	
	@Test
	void testBuscarPublicaciones() {
		BusquedaCompuesta busqueda = mock(BusquedaCompuesta.class);

		sitioWeb.buscarPublicaciones(busqueda);
		
		verify(busqueda, times(1)).filtrar(anyList());
	}

	@Test
	void testRegistrarCalificacion() {
		Calificable calificable = mock(Calificable.class);
		Calificacion calificacion = mock(Calificacion.class);
	
		sitioWeb.registrarCalificacion(calificable, calificacion);
		
		verify(gestor, times(1)).agregarCalificacion(calificable, calificacion);
	}
	
	@Test
	void testTopInquilinosQueMasAlquilaron() {
		Inquilino inquilino1 = mock(Inquilino.class);
		when(inquilino1.cantidadReservas()).thenReturn(1);
		when(inquilino1.esInquilino()).thenReturn(true);
		Inquilino inquilino2 = mock(Inquilino.class);
		when(inquilino2.cantidadReservas()).thenReturn(2);
		when(inquilino2.esInquilino()).thenReturn(true);
		
		sitioWeb.registrarUsuario(inquilino1);
		sitioWeb.registrarUsuario(inquilino2);
	
		List<Usuario> top = sitioWeb.topInquilinosQueMasAlquilaron();
		
		assertEquals(2, top.size());
		assertEquals(inquilino2, top.getFirst());
	}
	
	@Test
	void testPublicacionesSinReserva() {
		Publicacion publicacion1 = mock(Publicacion.class);
		when(publicacion1.tieneReservas()).thenReturn(false);
		Publicacion publicacion2 = mock(Publicacion.class);
		when(publicacion2.tieneReservas()).thenReturn(true);
		
		sitioWeb.altaDePublicacion(publicacion1);
		sitioWeb.altaDePublicacion(publicacion2);
		
		assertTrue(sitioWeb.publicacionesSinReserva().contains(publicacion1));
	}
	
	@Test
	void testTasaDeOcupacionMayorA0() {
		Publicacion publicacion1 = mock(Publicacion.class);
		when(publicacion1.tieneReservas()).thenReturn(true);
		Publicacion publicacion2 = mock(Publicacion.class);
		when(publicacion2.tieneReservas()).thenReturn(true);
		Publicacion publicacion3 = mock(Publicacion.class);
		when(publicacion3.tieneReservas()).thenReturn(false);
		Publicacion publicacion4 = mock(Publicacion.class);
		when(publicacion4.tieneReservas()).thenReturn(false);
	
		sitioWeb.altaDePublicacion(publicacion1);
		sitioWeb.altaDePublicacion(publicacion2);
		sitioWeb.altaDePublicacion(publicacion3);
		sitioWeb.altaDePublicacion(publicacion4);
		
		double resultadoObtenido = sitioWeb.tasaDeOcupacion();
		
		assertEquals(50, resultadoObtenido);
	}
	
	@Test
	void testTasaDeOcupacionIgualA0() {
		
		double resultadoObtenido = sitioWeb.tasaDeOcupacion();
		
		assertEquals(0, resultadoObtenido);
	}

}