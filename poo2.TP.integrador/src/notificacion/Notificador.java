package notificacion;

public interface Notificador {
	
	public void agregarSuscriptor(Suscriptor suscriptor);
	public void eliminarSuscriptor(Suscriptor suscriptor);
	public void notificarSuscriptores(String evento);
	
}
