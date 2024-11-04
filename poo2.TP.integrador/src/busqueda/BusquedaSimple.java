package busqueda;

import java.util.List;

import alquiler.Publicacion;

public abstract class BusquedaSimple implements CriterioBusqueda {
	
	public List<Publicacion> filtrar(List<Publicacion> publicaciones){
		
		return publicaciones.stream().filter(publicacion -> this.cumpleCondicion(publicacion)).toList();
	}

	public abstract boolean cumpleCondicion(Publicacion publicacion);
	
}
