package busqueda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import alquiler.Publicacion;

public class BusquedaCompuesta implements CriterioBusqueda {
	
	private String ciudadBuscada;
	private LocalDate fechaDesdeBuscada;
	private LocalDate fechaHastaBuscada;
	private List<CriterioBusqueda> criteriosAdicionales;
	
	public BusquedaCompuesta(String ciudadBuscada, LocalDate fechaDesdeBuscada, LocalDate fechaHastaBuscada) {
		this.ciudadBuscada = ciudadBuscada;
		this.fechaDesdeBuscada = fechaDesdeBuscada;
		this.fechaHastaBuscada = fechaHastaBuscada;
		this.criteriosAdicionales = new ArrayList<CriterioBusqueda>();
		this.inicializarCriteriosObligatorios();
	}

	private void inicializarCriteriosObligatorios() {
		this.agregarCriterio(new BusquedaCiudad(ciudadBuscada));
		this.agregarCriterio(new BusquedaRangoFechas(fechaDesdeBuscada , fechaHastaBuscada));
	}

	public void agregarCriterio(CriterioBusqueda criterio) {
		this.criteriosAdicionales.add(criterio);
	}
	
	public void eliminarCriterio(CriterioBusqueda criterio) {
		this.criteriosAdicionales.remove(criterio);
	}
	
	public List<CriterioBusqueda> getCriterios() {
		return this.criteriosAdicionales;
	}

	@Override
	public List<Publicacion> filtrar(List<Publicacion> publicaciones) {
		List<Publicacion> publicacionesFiltradas = new ArrayList<>(publicaciones);
		for (CriterioBusqueda criterio : criteriosAdicionales) {
			publicacionesFiltradas = criterio.filtrar(publicacionesFiltradas);
		}
		return publicacionesFiltradas;
	}
	
	
}
