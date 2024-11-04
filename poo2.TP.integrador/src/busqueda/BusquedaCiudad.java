package busqueda;

import alquiler.Publicacion;

public class BusquedaCiudad extends BusquedaSimple {
	
	private String ciudadBuscada;
	
	public BusquedaCiudad(String ciudadBuscada) {
		this.ciudadBuscada = ciudadBuscada;
	}

	@Override
	public boolean cumpleCondicion(Publicacion publicacion) {
		return publicacion.getInmueble().estaUbicadoEn(ciudadBuscada);
	}





}
