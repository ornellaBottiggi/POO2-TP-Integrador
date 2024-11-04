package busqueda;

import alquiler.Publicacion;

public class BusquedaCantHuespedes extends BusquedaSimple{
	
	private int cantHuespedesBuscada;
	
	public BusquedaCantHuespedes(int cantHuespedesBuscada) {
		this.cantHuespedesBuscada = cantHuespedesBuscada;
	}

	@Override
	public boolean cumpleCondicion(Publicacion publicacion) {
		return publicacion.getInmueble().permiteCantHuespedes(cantHuespedesBuscada);
	}
}
