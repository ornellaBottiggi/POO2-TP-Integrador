package busqueda;

import java.time.LocalDate;

import alquiler.Publicacion;

public class BusquedaRangoFechas extends BusquedaSimple {
	
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;

	public BusquedaRangoFechas(LocalDate fechaEntradaBuscada, LocalDate fechaSalidaBuscada) {
		this.fechaEntrada = fechaEntradaBuscada;
		this.fechaSalida = fechaSalidaBuscada;
	}
	
	@Override
	public boolean cumpleCondicion(Publicacion publicacion) {
		return publicacion.estaDisponible(fechaEntrada, fechaSalida);
	}
	
	
}
