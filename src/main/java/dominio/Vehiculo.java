package dominio;

public class Vehiculo {
	
	private String tipo;
	private String modelo;
	private String marca;
	private String placa;
	private Boolean estado;
	
	public Vehiculo(String tipo, String modelo, String marca, String placa, Boolean estado) {

		this.tipo = tipo;
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.estado = estado;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
}
