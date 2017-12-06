package dominio;

import java.util.Calendar;

public class CeldaParqueo {

	private Vehiculo vehiculo;
	private Calendar horaIngreso;
	private Calendar horaSalida;

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public Calendar getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(Calendar horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public Calendar getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Calendar horaSalida) {
		this.horaSalida = horaSalida;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public CeldaParqueo(Vehiculo vehiculo, Calendar horaIngreso, Calendar horaSalida) {
		this.vehiculo = vehiculo;
		this.horaIngreso = horaIngreso;
		this.horaSalida = horaSalida;
	}

}
