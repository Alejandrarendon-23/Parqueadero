package testdatabuilder;

import dominio.Vehiculo;

public class VehiculoTestDataBuilder {
	
	private static final boolean ESTADO = false;
	private static final String TIPO = "carro";
	private static final String PLACA = "FBW234";
	
	private String placa;
	private String tipo;
	private boolean estado;

	public VehiculoTestDataBuilder() {
		this.placa = PLACA;
		this.tipo = TIPO;
		this.estado = ESTADO;
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

	public Vehiculo build() {
		return new Vehiculo(this.tipo, this.placa,  this.estado);
	}

}
