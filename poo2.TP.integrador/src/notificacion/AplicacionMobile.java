package notificacion;

import alquiler.Publicacion;

public class AplicacionMobile implements Suscriptor {
	private PopUpWindow popUp;
	
	public AplicacionMobile(PopUpWindow popUp) {
		this.popUp = popUp;
	}
	
	@Override
	public void actualizar(String evento, Publicacion publicacion) {
		if (evento.equalsIgnoreCase("cancelación de reserva")) {
			this.enviarMensaje(publicacion);
		}
	}
	
	private void enviarMensaje(Publicacion publicacion) {
		String tipoInmueble = publicacion.getInmueble().getTipoInmueble();
		String mensaje = "El/la " + tipoInmueble + " que te interesa se ha liberado! Corre a reservarlo!";
	    popUp.popUp(mensaje, "red", 12);
	}
	
}
