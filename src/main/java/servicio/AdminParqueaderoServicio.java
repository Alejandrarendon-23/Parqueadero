package servicio;

import java.util.List;

import dominio.Vehiculo;
import dominio.CeldaParqueo;

public interface AdminParqueaderoServicio {
	
	public  List<Vehiculo> listarVehiculos();
	public  boolean esPermitidoIngresoPorPlaca(String placa);
	public  boolean esMayorAlCilindrajePermitido(int cilindraje);
	public  int calcularValorTotalPorTiempo(CeldaParqueo celda);
	public void ingresarVehiculo(Vehiculo vehiculo);
	
	
	 
	
		
		
	

}
