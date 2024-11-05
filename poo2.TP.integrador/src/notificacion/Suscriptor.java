package notificacion;

import alquiler.Publicacion;

public interface Suscriptor {
	public void actualizar(String evento, Publicacion publicacion);
	
}
