package busqueda;

import java.util.List;

import alquiler.Publicacion;

public interface CriterioBusqueda {
	
	public List<Publicacion> filtrar(List<Publicacion> publicaciones);
}
