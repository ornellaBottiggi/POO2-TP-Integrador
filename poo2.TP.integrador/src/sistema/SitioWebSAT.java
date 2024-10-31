package sistema;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public List<Inquilino> inquilinosQueMasAlquilaron(){
		
	}
	
	public List<Publicacion> publicacionesSinReserva(){
		
	}
	
	public double tasaDeOcupacion() {
		
	}

















}














