package testdatabuilder;

import dominio.Moto;

public class MotoTestDataBuilder {
	
	private static final boolean ESTADO = false;
	private static final String TIPO = "moto";
	private static final String PLACA = "FBW23D";
	private static final int CILINDRAJE = 1000;
	
	private String placa;
	private String tipo;
	private boolean estado;
	private int cilindraje;

	public MotoTestDataBuilder() {
		this.placa = PLACA;
		this.tipo = TIPO;
		this.estado = ESTADO;
		this.cilindraje= CILINDRAJE;
	}

	public MotoTestDataBuilder conTipo(String tipo) {
		this.tipo = tipo;
		return this;
	}

	public MotoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public MotoTestDataBuilder conEstado(boolean estado) {
		this.estado = estado;
		return this;
	}
	public MotoTestDataBuilder conCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public Moto build() {
		return new Moto(this.tipo, this.placa,  this.estado, this.cilindraje);
	}


}
