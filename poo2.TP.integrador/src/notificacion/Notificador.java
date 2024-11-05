package notificacion;

import alquiler.Publicacion;

public interface Notificador {
	
	public void agregarSuscriptor(Suscriptor suscriptor);
	public void eliminarSuscriptor(Suscriptor suscriptor);
	public void notificarSuscriptores(String evento, Publicacion publicacion);
	
}
