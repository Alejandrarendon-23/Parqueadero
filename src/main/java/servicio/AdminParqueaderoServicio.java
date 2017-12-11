package servicio;

import java.util.List;

import dominio.Vehiculo;
import persistencia.entidad.VehiculoEntity;
import dominio.CeldaParqueo;

public interface AdminParqueaderoServicio {
	
	public  List<VehiculoEntity> listarVehiculos();
	public  boolean esPermitidoIngresoPorPlaca(String placa);
	public  boolean esMayorAlCilindrajePermitido(int cilindraje);
	public  int calcularValorTotalPorTiempo(CeldaParqueo celda);
	public String ingresarVehiculo(Vehiculo vehiculo);
	public int precioTotalPorVehiculo(CeldaParqueo celda);
	public int obtenerCantidadCarros();
	public int obtenerCantidadMotos();
	public String retirarVehiculo(Vehiculo vehiculo);
}
