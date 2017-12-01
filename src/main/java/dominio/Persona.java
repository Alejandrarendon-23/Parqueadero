package dominio;


import dominio.repositorio.RepositorioVehiculo;

public class Persona {
	
	private String nombre;
	private String identificaci�n;
	private RepositorioVehiculo repositorioVehiculo;
	
	
	public Persona (String nombre, String identificacion) {
		this.nombre = nombre;
		this.identificaci�n = identificacion;
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdentificaci�n() {
		return identificaci�n;
	}
	public void setIdentificaci�n(String identificaci�n) {
		this.identificaci�n = identificaci�n;
	}
	
	
	

}
