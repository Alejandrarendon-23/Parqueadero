package dominio;

public class Vehiculo {
	
	private String tipo;
	private String placa;
	private Boolean estado;
	
	public Vehiculo(String tipo, String placa, Boolean estado) {

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
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
}
