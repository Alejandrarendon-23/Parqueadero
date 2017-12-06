package testdatabuilder;

import java.util.Calendar;

import dominio.Carro;
import dominio.CeldaParqueo;
import dominio.Vehiculo;


public class CeldaParqueoTestDataBuilder {

	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private Vehiculo vehiculo;

	public CeldaParqueoTestDataBuilder() {
		this.fechaIngreso = Calendar.getInstance();
		this.fechaSalida = Calendar.getInstance();		
		this.vehiculo = new Carro("carro", "ASS123", true);
	}

	public CeldaParqueoTestDataBuilder conFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public CeldaParqueoTestDataBuilder conFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public CeldaParqueoTestDataBuilder conVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}

	public CeldaParqueo build() {
		CeldaParqueo celdaParqueo = new CeldaParqueo(this.vehiculo, this.fechaIngreso, this.fechaSalida);
		return celdaParqueo;
	}

}
