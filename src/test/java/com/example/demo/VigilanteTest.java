package com.example.demo;

import org.junit.Test;


import dominio.Vehiculo;
import dominio.Vigilante;

public class VigilanteTest {
	
	@Test
	public void ingresarCarro() {
		Vehiculo carro = new Vehiculo("carro", "KAS902", false);
		Vigilante vigilante = new Vigilante();
		vigilante.ingresarVehiculo(carro);
		
	}
}
