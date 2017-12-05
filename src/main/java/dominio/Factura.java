package dominio;

import java.util.Date;


public class Factura {
	
	private Vehiculo vehiculo;
	private int precio;
	private Date HoraIngreso;
	private Date HoraSalida;
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public Date getHoraIngreso() {
		return HoraIngreso;
	}
	public void setHoraIngreso(Date horaIngreso) {
		HoraIngreso = horaIngreso;
	}
	public Date getHoraSalida() {
		return HoraSalida;
	}
	public void setHoraSalida(Date horaSalida) {
		HoraSalida = horaSalida;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public Factura(Vehiculo vehiculo, Date horaIngreso, Date horaSalida, int precio) {
		super();
		this.vehiculo = vehiculo;
		this.HoraIngreso = horaIngreso;
		this.HoraSalida = horaSalida;
		this.precio = precio;
	}
	
	
	
	
}
