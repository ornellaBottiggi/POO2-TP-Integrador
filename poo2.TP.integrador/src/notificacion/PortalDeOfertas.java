package notificacion;

import alquiler.Publicacion;

public class PortalDeOfertas implements Suscriptor {
	private HomePagePublisher publisher; 
	
	public PortalDeOfertas(HomePagePublisher publisher) {
		this.publisher = publisher;
		
	}
	
	@Override
	public void actualizar(String evento, Publicacion publicacion) {
		if (evento.equalsIgnoreCase("baja de precio")) {
			this.publicarMensaje(publicacion);
		}	
	}
	
	private void publicarMensaje(Publicacion publicacion) {
		String tipoInmueble = publicacion.getInmueble().getTipoInmueble();
		double precio = publicacion.getPrecioBase();
		String mensaje = "No te pierdas esta oferta: Un inmueble " + tipoInmueble + " a tan solo " + precio + " pesos.";
		publisher.publish(mensaje);
	}
}