package servicio;

import java.util.List;

import dominio.Vehiculo;

public interface AdminParqueaderoServicio {
	
	public abstract List<Vehiculo> listarVehiculos();
	public abstract boolean validarPlacas(String placa);
	public abstract boolean validarCilindraje(Vehiculo moto);
	
		
		
	

}
