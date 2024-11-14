package busqueda;

import alquiler.Publicacion;

public class BusquedaPrecioMinimo extends BusquedaSimple {
	
	private double precioBuscado;
	
	public BusquedaPrecioMinimo(double precioBuscado) {
		this.precioBuscado = precioBuscado;
	}

	@Override
	public boolean cumpleCondicion(Publicacion publicacion) {
		return publicacion.getPrecioBase() >= precioBuscado;
	}

}
