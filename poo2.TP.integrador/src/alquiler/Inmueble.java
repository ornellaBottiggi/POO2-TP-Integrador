package alquiler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import enums.Servicio;
import sistema.SitioWebSAT;
import usuario.Calificable;

public class Inmueble implements Calificable{
	private String tipoInmueble;
	private double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private Set<Servicio> servicios;
	private int capacidad;
	private List<String> fotos;
	private int vecesAlquilado;

	public Inmueble(String tipo, double superficie, String pais, String ciudad, String direccion, int capacidad) {
		this.tipoInmueble = tipo;
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = new HashSet<Servicio>();
		this.capacidad = capacidad;
		this.fotos = new ArrayList<String>();
		this.vecesAlquilado = 0;
	}
	
	public String getTipoInmueble() {
		return this.tipoInmueble;
	}
	
	public double getSuperficie() {
		return this.superficie;
	}
	
	public String getPais() {
		return this.pais;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}
	
	public String getDireccion() {
		return this.direccion;
	}
	
	public Set<Servicio> getServicios() {
		return this.servicios;
	}
	
	public int getCapacidad() {
		return this.capacidad;
	}
	
	public List<String> getFotos() {
		return this.fotos;
	}
	
	public int getVecesAlquilado() {
		return this.vecesAlquilado;
	}
	
	public void agregarServicio(Servicio servicio) {
		this.getServicios().add(servicio);
	}
	
	public int cantidadDeFotos() {
		return this.getFotos().size();
	}
	
	public void agregarFoto(String foto) {
		if(this.cantidadDeFotos() < 5) {
			this.getFotos().add(foto);
		} else {
			throw new RuntimeException("No pueden cargarse más fotos");
		}
	}

	public boolean fueAlquilado() {
		return this.getVecesAlquilado() > 0;
	}
	
	public void incrementarVecesAlquilado() {
		this.vecesAlquilado++;
	}
	
	@Override
	public void calificar(SitioWebSAT sitioWeb, Reserva reserva, Calificable entidad, String categoria, int puntaje, String comentario) {
		throw new RuntimeException("Un inmueble no puede calificar a otros.");
	}

	public boolean estaUbicadoEn(String ciudadBuscada) {
		return this.getCiudad().equals(ciudadBuscada);
	}

	public boolean permiteCantHuespedes(int cantHuespedesBuscada) {
		return this.getCapacidad() >= cantHuespedesBuscada;
	}

}
