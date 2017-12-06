package dominio;

public class Vehiculo {

	private String tipo;
	private String placa;
	private boolean estado;

	public Vehiculo() {

	}

	public Vehiculo(String tipo, String placa, boolean estado) {

		this.tipo = tipo;
		this.placa = placa;
		this.estado = estado;

	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
