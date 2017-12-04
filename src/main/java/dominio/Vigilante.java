package dominio;

import dominio.repositorio.RepositorioVehiculo;

public class Vigilante {
	
	private String nombre;
	private String identificacion;
	
	public Vigilante (String nombre, String identificacion) {
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

	RepositorioVehiculo repositoriovehiculo;
	
	public void ingresarVehiculo(Vehiculo moto) {
		repositoriovehiculo.agregar(moto);
	}

	public Vigilante(RepositorioVehiculo repositoriovehiculo) {
		super();
		this.repositoriovehiculo = repositoriovehiculo;
	}
	
	
}
