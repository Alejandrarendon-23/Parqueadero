package servicio;

import java.util.List;

import dominio.Vehiculo;

public interface AdminParqueaderoServicio {
	
	public  List<Vehiculo> listarVehiculos();
	public  boolean esPermitidoIngresoPorPlaca(String placa);
	public  boolean esMayorAlCilindrajePermitido(int cilindraje);
	public  int calcularPrecioTotal();
	public void ingresarVehiculo(Vehiculo vehiculo);
	public boolean valorPorHora(String tipo, boolean estado);
	public boolean valorPorDia(String tipo, boolean estado);
	
	 
	
		
		
	

}
