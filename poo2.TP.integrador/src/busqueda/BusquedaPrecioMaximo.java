package busqueda;

import alquiler.Publicacion;

public class BusquedaPrecioMaximo extends BusquedaSimple {
	
	private double precioBuscado;
	
	public BusquedaPrecioMaximo(double precioBuscado) {
		this.precioBuscado = precioBuscado;
	}

	@Override
	public boolean cumpleCondicion(Publicacion publicacion) {
		return publicacion.getPrecioBase() >= precioBuscado;
	}

}
