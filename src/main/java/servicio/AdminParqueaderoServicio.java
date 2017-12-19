package servicio;

import java.util.List;

import dominio.CeldaParqueo;
import dominio.Vehiculo;
import persistencia.entidad.CeldaEntity;
import persistencia.entidad.VehiculoEntity;

public interface AdminParqueaderoServicio {
	
	public  List<VehiculoEntity> listarVehiculos();
	public  List<CeldaEntity> listarCeldas();
	public  boolean esPermitidoIngresoPorPlaca(String placa);
	public  boolean esMayorAlCilindrajePermitido(int cilindraje);
	public  int calcularValorTotalPorTiempo(CeldaParqueo celda);
	public void ingresarVehiculo(Vehiculo vehiculo);
	public int precioTotalPorVehiculo(CeldaParqueo celda);
	public int obtenerCantidadCarros();
	public int obtenerCantidadMotos();
	public String retirarVehiculo(Vehiculo vehiculo);
	public int cobroTotalPorVehiculo(CeldaParqueo celda);
	
}
