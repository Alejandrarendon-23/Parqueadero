package testdatabuilder;

import dominio.Vehiculo;

public class CarroTestDataBuilder {
	private static final boolean ESTADO = false;
	private static final String TIPO = "carro";
	private static final String PLACA = "FBW234";
	
	private String placa;
	private String tipo;
	private boolean estado;

	public CarroTestDataBuilder() {
		this.placa = PLACA;
		this.tipo = TIPO;
		this.estado = ESTADO;
	}

	public CarroTestDataBuilder conTipo(String tipo) {
		this.tipo = tipo;
		return this;
	}

	public CarroTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public CarroTestDataBuilder conEstado(boolean estado) {
		this.estado = estado;
		return this;
	}

	public Vehiculo build() {
		return new Vehiculo(this.tipo, this.placa,  this.estado);
	}


}
