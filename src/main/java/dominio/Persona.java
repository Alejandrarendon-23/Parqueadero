package dominio;


import dominio.repositorio.RepositorioVehiculo;

public class Persona {
	
	private String nombre;
	private String identificación;
	private RepositorioVehiculo repositorioVehiculo;
	
	
	public Persona (String nombre, String identificacion) {
		this.nombre = nombre;
		this.identificación = identificacion;
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIdentificación() {
		return identificación;
	}
	public void setIdentificación(String identificación) {
		this.identificación = identificación;
	}
	
	
	

}
