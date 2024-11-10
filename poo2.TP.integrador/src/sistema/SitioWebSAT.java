package sistema;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import alquiler.Publicacion;
import busqueda.BusquedaCompuesta;
import enums.Servicio;
import usuario.Calificable;
import usuario.Inquilino;
import usuario.Usuario;

public class SitioWebSAT {
	
	private GestorCalificaciones gestorCalificaciones;
	private List<String> categorias;
	private Set<Usuario> usuarios;
	private List<Publicacion> publicaciones;
	private List<String> tiposInmuebles;
	private List<Servicio> servicios;
	
	
	public SitioWebSAT(GestorCalificaciones gestor) {
		this.gestorCalificaciones = gestor;
		this.categorias = new ArrayList<String>();
		this.usuarios = new HashSet<Usuario>();
		this.publicaciones = new ArrayList<Publicacion>();
		this.tiposInmuebles = new ArrayList<String>();
		this.servicios = new ArrayList<Servicio>();
	}
	
	public GestorCalificaciones getGestorCalificaciones() {
		return this.gestorCalificaciones;
	}
	
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}
	
	public List<Publicacion> getPublicaciones() {
		return this.publicaciones;
	}
	
	public List<String> getTiposInmuebles() {
		return this.tiposInmuebles;
	}
	
	public List<Servicio> getServicios() {
		return this.servicios;
	}
	
	public List<String> getCategorias(){
		return this.categorias;
	}
	
	public void registrarUsuario(Usuario usuario) {
		this.getUsuarios().add(usuario);
	}
	
	public void altaDePublicacion(Publicacion publicacion) {
		this.getPublicaciones().add(publicacion);
	}

	public void altaDeCategoria(String categoria) {
		this.getCategorias().add(categoria);
	}
	
	public void altaDeServicio(Servicio servicio) {
		this.getServicios().add(servicio);
	}
	
	public void altaDeTipoDeInmueble(String tipoDeInmueble) {
		this.getTiposInmuebles().add(tipoDeInmueble);
	}
	
	public List<Publicacion> buscarPublicaciones(BusquedaCompuesta busqueda) {
		return busqueda.filtrar(getPublicaciones());
	}
	
	public void registrarCalificacion(Calificable calificable, Calificacion calificacion) {
		this.getGestorCalificaciones().agregarCalificacion(calificable, calificacion);
		
	}
	
	public List<Usuario> topInquilinosQueMasAlquilaron() {
		List<Usuario> inquilinosOrdenados = this.ordenarInquilinosPorReservas();
		return inquilinosOrdenados.stream().limit(10).collect(Collectors.toList());
	}
	
	public List<Usuario> ordenarInquilinosPorReservas(){
		List<Usuario> inquilinos = this.getUsuarios().stream().filter(usuario -> usuario.esInquilino()).toList();
		inquilinos.sort((inquilino1 , inquilino2) -> Integer.compare(inquilino1.cantidadReservas(), inquilino2.cantidadReservas()));
		return inquilinos;
	}
	
	public List<Publicacion> publicacionesSinReserva() {
		return getPublicaciones().stream().filter(publicacion -> !publicacion.tieneReservas()).toList();
	}
	
	public List<Publicacion> publicacionesConReserva() {
		return getPublicaciones().stream().filter(publicacion -> publicacion.tieneReservas()).toList();
	}
	
	public int totalPublicaciones() {
		return getPublicaciones().size();
	}
	
	public int totalPublicacionesConReserva() {
		return this.publicacionesConReserva().size();
	}
	
	public double tasaDeOcupacion() {
		return this.totalPublicacionesConReserva() / this.totalPublicaciones();
	}

}
