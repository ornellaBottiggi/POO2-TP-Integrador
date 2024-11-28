package sistema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import alquiler.Publicacion;
import busqueda.BusquedaCompuesta;
import enums.Entidad;
import enums.Servicio;
import usuario.Usuario;

public class SitioWebSAT {
	
	private Set<Usuario> usuarios;
	private List<Publicacion> publicaciones;
	private List<String> tiposInmuebles;
	private List<Servicio> servicios;
	private Map<Entidad, List<String>> categoriasEntidades;
	
	
	public SitioWebSAT() {
		this.usuarios = new HashSet<Usuario>();
		this.publicaciones = new ArrayList<Publicacion>();
		this.tiposInmuebles = new ArrayList<String>();
		this.servicios = new ArrayList<Servicio>();
		this.categoriasEntidades = new HashMap<Entidad, List<String>>();
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
	
	public Map<Entidad, List<String>> getCategoriasEntidades(){
		return this.categoriasEntidades;
	}
	
	public void registrarUsuario(Usuario usuario) {
		this.getUsuarios().add(usuario);
	}
	
	public void altaDePublicacion(Publicacion publicacion) {
		this.getPublicaciones().add(publicacion);
	}

	public void altaDeCategoria(String categoria, Entidad entidad) {
		List<String> categorias = categoriasEntidades.computeIfAbsent(entidad, k -> new ArrayList<String>());
		categorias.add(categoria);
	}
	
	public boolean esCategoriaValida(String categoria, Entidad entidad) {
		List<String> categorias = categoriasEntidades.get(entidad);
		return categorias.contains(categoria);
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
	
	public List<Usuario> topInquilinosQueMasAlquilaron() {
		List<Usuario> inquilinosOrdenados = this.ordenarInquilinosPorReservas();
		return inquilinosOrdenados.stream().limit(10).collect(Collectors.toList());
	}
	
	public List<Usuario> ordenarInquilinosPorReservas(){
		List<Usuario> inquilinos = this.getUsuarios().stream().filter(usuario -> usuario.tieneReservas()).collect(Collectors.toList());
		inquilinos.sort((inquilino1 , inquilino2) -> Integer.compare(inquilino2.cantidadReservas(), inquilino1.cantidadReservas()));
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
		int total = this.totalPublicaciones();
		
		return (total > 0) ? (this.totalPublicacionesConReserva() / (double) total) * 100 : 0;
	}

}
