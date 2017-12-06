package testdatabuilder;

import dominio.Carro;
import dominio.Moto;
import dominio.Vehiculo;

public class VehiculoTestDataBuilder {
	
	private static final boolean ESTADO = true;
	private static final String TIPO = "carro";
	private static final String PLACA = "FBW234";
	private static final int CILINDRAJE = 100;

	
	private String placa;
	private String tipo;
	private boolean estado;
	private int cilindraje;

	public VehiculoTestDataBuilder() {
		this.placa = PLACA;
		this.tipo = TIPO;
		this.estado = ESTADO;
		this.cilindraje = CILINDRAJE;
	}

	public VehiculoTestDataBuilder conTipo(String tipo) {
		this.tipo = tipo;
		return this;
	}

	public VehiculoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoTestDataBuilder conEstado(boolean estado) {
		this.estado = estado;
		return this;
	}
	public VehiculoTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}
	

	public Vehiculo build() {
		if(this.tipo.equals("carro")) {
			return new Carro(this.tipo, this.placa,  this.estado);
		}else if(this.tipo.equals("moto")) {
			return new Moto(this.tipo, this.placa,  this.estado, this.cilindraje);
		}else {
			return new Carro(this.tipo, this.placa,  this.estado);
		}
			
		
	}

}
