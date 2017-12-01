package dominio;


import dominio.repositorio.RepositorioVehiculo;

public class Persona {
	
	private String nombre;
	private String identificacion;
	private RepositorioVehiculo repositorioVehiculo;
	
	
	public Persona (String nombre, String identificacion) {
		this.nombre = nombre;
		this.identificacion = identificacion;
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	
	

}
