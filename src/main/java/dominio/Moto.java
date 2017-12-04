package dominio;

public class Moto extends Vehiculo {
	
	private int cilindraje;

	
	public Moto(String tipo, String placa, boolean estado, int cilindraje) {
		super(tipo, placa, estado);
		this.cilindraje = cilindraje;
	}
	
	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}


}
