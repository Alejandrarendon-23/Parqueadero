package servicio;

import java.util.List;

import dominio.Vehiculo;

public class AdminParqueaderoServicioImpl implements AdminParqueaderoServicio {
	
	private static final int MAX_CARROS = 20;
	private static final int MAX_MOTOS = 10;
	private static final float VALOR_HORA_CARRO = 1000;
	private static final float VALOR_DIA_CARRO = 8000;
	private static final float VALOR_HORA_MOTO = 500;
	

	@Override
	public List<Vehiculo> listarVehiculos() {
		
		return null;
	}


	@Override
	public boolean validarPlacas(String placa) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean validarCilindraje(Vehiculo moto) {
		// TODO Auto-generated method stub
		return false;
	}

}
