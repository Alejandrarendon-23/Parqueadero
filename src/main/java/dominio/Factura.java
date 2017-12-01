package dominio;

import java.util.Date;

public class Factura {
	
	private Vehiculo vehiculo;
	private  Date HoraIngreso;
	private Date HoraSalida;
	private int precio;
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
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
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public Factura(Vehiculo vehiculo, Date horaIngreso, Date horaSalida, int precio) {
		super();
		this.vehiculo = vehiculo;
		HoraIngreso = horaIngreso;
		HoraSalida = horaSalida;
		this.precio = precio;
	}
	
	
	
	
}
