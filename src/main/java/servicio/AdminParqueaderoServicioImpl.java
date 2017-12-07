package servicio;

import java.util.Calendar;
import java.util.List;

import dominio.Carro;
import dominio.CeldaParqueo;
import dominio.Moto;
import dominio.Vehiculo;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.sistema.SistemaPersistencia;

public class AdminParqueaderoServicioImpl implements AdminParqueaderoServicio {

	private static final int MAX_CARROS = 20;
	private static final int MAX_MOTOS = 10;
	private static final int VALOR_HORA_CARRO = 1000;
	private static final int VALOR_DIA_CARRO = 8000;
	private static final int VALOR_HORA_MOTO = 500;
	private static final int VALOR_DIA_MOTO = 8000;
	private static final int MAX_CILINDRAJE = 500;
	private RepositorioVehiculo repositoriovehiculo;

	public AdminParqueaderoServicioImpl() {
		SistemaPersistencia sistemapersistencia = new SistemaPersistencia();
		this.repositoriovehiculo = sistemapersistencia.obtenerRepositorioVehiculo();
	}

	@Override
	public List<Vehiculo> listarVehiculos() {
		return repositoriovehiculo.listarvehiculos();

	}

	@Override
	public boolean esMayorAlCilindrajePermitido(int cilindraje) {

		if (cilindraje > MAX_CILINDRAJE) {

			return true;
		}
		return false;
	}

	@Override
	public int calcularValorTotalPorTiempo(CeldaParqueo celda) {
		Calendar fechaIngreso = celda.getHoraIngreso();
		Calendar fechaSalida = celda.getHoraSalida();
		long fechaDiferencia = (fechaSalida.getTimeInMillis() - fechaIngreso.getTimeInMillis());
		int numDias = 0;
		int numHorasRestantes = 0;
		int numHoras = (int) (fechaDiferencia / (1000 * 60 * 60));
		int valorHora = 0;
		int valorDia = 0;
		if (celda.getVehiculo().getTipo().equals("carro")) {
			valorDia = VALOR_DIA_CARRO;
			valorHora = VALOR_HORA_CARRO;
		} else {
			valorDia = VALOR_DIA_MOTO;
			valorHora = VALOR_HORA_MOTO;
		}
		int suma = 0;
		if (numHoras < 9) {
			suma = numHoras * valorHora;
		} else if (numHoras >= 9 && numHoras < 24) {
			suma = valorDia;
		} else {
			numDias = numHoras / 24;
			suma = numDias * valorDia;
			numHorasRestantes = numHoras % 24;
		}
		if (numHorasRestantes < 9) {
			suma = suma + (numHorasRestantes * valorHora);
		} else if (numHorasRestantes >= 9 && numHorasRestantes < 24) {
			suma = suma + valorDia;

		}
		return suma;
	}

	@Override
	public boolean esPermitidoIngresoPorPlaca(String placa) {
		Calendar now = Calendar.getInstance();
		if ((placa.charAt(0) == 'A')
				&& ((now.get((Calendar.DAY_OF_WEEK) - 1) != 1) || (now.get((Calendar.DAY_OF_WEEK) - 1) != 2))) {
			System.err.println("No est� autorizado para ingresar al parqueadero");
			return false;
		}
		return true;
	}

	public String ingresarVehiculo(Vehiculo vehiculo) {
		if (vehiculo.getTipo().equals("carro") && esPermitidoIngresoPorPlaca((vehiculo.getPlaca()))
				&& obtenerCantidadCarros() <= MAX_CARROS) {

			repositoriovehiculo.agregar(vehiculo);
			
		} else if (vehiculo.getTipo().equals("moto") && esPermitidoIngresoPorPlaca((vehiculo.getPlaca()))
				&& obtenerCantidadCarros() <= MAX_MOTOS) {

			repositoriovehiculo.agregar(vehiculo);
		}
		return "No se permite su ingreso al parqueadero";
	}

	public void retirarVehiculo(Vehiculo vehiculo) {

		repositoriovehiculo.actualizarVehiculo(vehiculo);
	}

	@Override
	public int precioTotalPorVehiculo(CeldaParqueo celda) {
		int precioTotal = 0;
		if (celda.getVehiculo().getTipo().equals("moto")
				&& esMayorAlCilindrajePermitido(((Moto) celda.getVehiculo()).getCilindraje())) {
			precioTotal = calcularValorTotalPorTiempo(celda) + 2000;
		} else {
			precioTotal = calcularValorTotalPorTiempo(celda);
		}
		return precioTotal;
	}

	@Override
	public int obtenerCantidadCarros() {
		List<Vehiculo> vehiculosAValidar = listarVehiculos();
		int contadorCarros = 0;
		for (Vehiculo vehiculo : vehiculosAValidar) {
			if (vehiculo.getTipo().equals("carro")) {
				contadorCarros += 1;

			}
		}

		return contadorCarros;
	}

	@Override
	public int obtenerCantidadMotos() {
		List<Vehiculo> vehiculosAValidar = listarVehiculos();
		int contadorMotos = 0;
		for (Vehiculo vehiculo : vehiculosAValidar) {
			if (vehiculo.getTipo().equals("moto")) {
				contadorMotos += 1;

			}
		}

		return contadorMotos;
	}

}
