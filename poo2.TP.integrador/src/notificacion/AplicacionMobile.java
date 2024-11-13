package notificacion;

import alquiler.Publicacion;
import alquiler.Reserva;

public class AplicacionMobile implements Suscriptor {
	private PopUpWindow popUp;
	
	public AplicacionMobile(PopUpWindow popUp) {
		this.popUp = popUp;
	}
	
	@Override
	public void cambioDePrecio(Publicacion publicacion, double nuevoPrecio) {
		System.out.println("no está interesado en este evento");
	}

	@Override
	public void cancelacionReserva(Reserva reserva) {
		String tipoInmueble = reserva.getInmueble().getTipoInmueble();
		String mensaje = "El/la " + tipoInmueble + " que te interesa se ha liberado! Corre a reservarlo!";
	    popUp.popUp(mensaje, "red", 12);
	}

	@Override
	public void reservaInmueble(Reserva reserva) {
		System.out.println("no está interesado en este evento");
	}
	
}
