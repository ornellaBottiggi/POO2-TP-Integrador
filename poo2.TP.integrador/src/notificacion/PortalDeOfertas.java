package notificacion;

import alquiler.Publicacion;
import alquiler.Reserva;

public class PortalDeOfertas implements Suscriptor {
	private HomePagePublisher publisher; 
	
	public PortalDeOfertas(HomePagePublisher publisher) {
		this.publisher = publisher;
	}
	
	@Override
	public void cambioDePrecio(Publicacion publicacion) {
		double nuevoPrecio = publicacion.getPrecioBase();
		String tipoInmueble = publicacion.getInmueble().getTipoInmueble();
		String mensaje = "No te pierdas esta oferta: Un inmueble " + tipoInmueble + " a tan solo " + nuevoPrecio + " pesos.";
		publisher.publish(mensaje);
	}

	@Override
	public void cancelacionReserva(Reserva reserva) {
		System.out.println("no está interesado en este evento");
	}

	@Override
	public void reservaInmueble(Reserva reserva) {
		System.out.println("no está interesado en este evento");
	}
}