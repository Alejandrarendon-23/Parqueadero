package servicio;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import dominio.CeldaParqueo;
import dominio.Moto;
import dominio.Vehiculo;
import dominio.repositorio.RepositorioCelda;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.builder.VehiculoBuilder;
import persistencia.entidad.CeldaEntity;
import persistencia.entidad.VehiculoEntity;
import persistencia.sistema.SistemaPersistencia;

@Component
public class AdminParqueaderoServicioImpl implements AdminParqueaderoServicio {

	private static final String MOTO = "moto";
	private static final String CARRO = "carro";
	private static final int MAX_CARROS = 20;
	private static final int MAX_MOTOS = 10;
	private static final int VALOR_HORA_CARRO = 1000;
	private static final int VALOR_DIA_CARRO = 8000;
	private static final int VALOR_HORA_MOTO = 500;
	private static final int VALOR_DIA_MOTO = 4000;
	private static final int MAX_CILINDRAJE = 500;
	private RepositorioVehiculo repositoriovehiculo;
	private RepositorioCelda repositoriocelda;

	public AdminParqueaderoServicioImpl() {
		SistemaPersistencia sistemapersistencia = new SistemaPersistencia();
		this.repositoriovehiculo = sistemapersistencia.obtenerRepositorioVehiculo();
		this.repositoriocelda = sistemapersistencia.obtenerRepositorioCelda();
	}

	@Override
	public List<VehiculoEntity> listarVehiculos() {
		return repositoriovehiculo.listarvehiculos();

	}

	@Override
	public List<CeldaEntity> listarCeldas() {
		return repositoriocelda.listarcelda();

	}

	@Override
	public boolean esMayorAlCilindrajePermitido(int cilindraje) {

		return (cilindraje >= MAX_CILINDRAJE);
	}

	@Override
	public int calcularValorTotalPorTiempo(CeldaParqueo celda) {
		Calendar fechaIngreso = celda.getHoraIngreso();
		Calendar fechaSalida = celda.getHoraSalida();
		long fechaDiferencia = (fechaSalida.getTimeInMillis() - fechaIngreso.getTimeInMillis());
		int numHorasRestantes = 0;
		int numHoras = (int) (fechaDiferencia / (1000 * 60 * 60));
		int minutos = (int) ((fechaDiferencia % (1000 * 60 * 60)) / (1000 * 60));
		int valorHora = 0;
		int valorDia = 0;
		if (celda.getVehiculo().getTipo().equals(CARRO)) {
			valorDia = VALOR_DIA_CARRO;
			valorHora = VALOR_HORA_CARRO;
		} else {
			valorDia = VALOR_DIA_MOTO;
			valorHora = VALOR_HORA_MOTO;
		}

		return valorTotalPorTiempo(numHorasRestantes, numHoras, minutos, valorHora, valorDia);
	}

	private int valorTotalPorTiempo(int numHorasRestantes, int numHoras, int minutos, int valorHora, int valorDia) {
		int numDias = 0;
		int suma = 0;
		if (minutos > 0) {
			suma += valorHora;
		}
		if (numHoras < 9) {
			suma += numHoras * valorHora;

		} else if (numHoras >= 9 && numHoras < 24) {
			suma += valorDia;
		} else {
			numDias = numHoras / 24;
			suma += numDias * valorDia;
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

		if ((placa.charAt(0) == 'A')) {

			return ((now.get((Calendar.DAY_OF_WEEK)) != 1) && (now.get((Calendar.DAY_OF_WEEK)) != 2));
		}
		return true;

	}

	public void ingresarVehiculo(Vehiculo vehiculo) {

		validarSiElVehiculoPuedeIngresar(vehiculo);

		ingresarVehiculoBd(vehiculo);

	}

	private void validarSiElVehiculoPuedeIngresar(Vehiculo vehiculo) {
		int capacidadMaxima = CARRO.equals(vehiculo.getTipo()) ? MAX_CARROS : MAX_MOTOS;
		if (obtenerCantidadCarros() > capacidadMaxima || vehiculo.getEstado()
				|| !esPermitidoIngresoPorPlaca((vehiculo.getPlaca()))) {
			throw new IllegalArgumentException("no hay acceso de " + vehiculo.getTipo());
		}
	}

	private String ingresarVehiculoBd(Vehiculo vehiculo) {
		String mensaje;
		vehiculo.setEstado(true);

		repositoriovehiculo.agregar(VehiculoBuilder.convertirAEntity(vehiculo));
		CeldaEntity celdaEntity = new CeldaEntity();
		VehiculoEntity vehiculoEntity = repositoriovehiculo.obtenerPorPlaca(vehiculo.getPlaca());

		celdaEntity.setHoraIngreso(Calendar.getInstance());
		celdaEntity.setVehiculo(vehiculoEntity);

		repositoriocelda.agregarCelda(celdaEntity);
		mensaje = ("Está autorizado para ingresar al parqueadero");
		return mensaje;
	}

	@Override
	public String retirarVehiculo(Vehiculo vehiculo) {
		String mensaje = "No es posible retirar el vehiculo";
		if (vehiculo.getEstado()) {
			vehiculo.setEstado(false);

			repositoriovehiculo.actualizarVehiculo(VehiculoBuilder.convertirAEntity(vehiculo));
			
			mensaje = "retiro exitoso";
		}
		return mensaje;

	}

	@Override
	public int precioTotalPorVehiculo(CeldaParqueo celda) {
		int precioTotal = 0;
		if (celda.getVehiculo().getTipo().equals(MOTO)
				&& esMayorAlCilindrajePermitido(((Moto) celda.getVehiculo()).getCilindraje())) {
			precioTotal = calcularValorTotalPorTiempo(celda) + 2000;
		} else {
			precioTotal = calcularValorTotalPorTiempo(celda);
		}
		return precioTotal;
	}

	@Override
	public int obtenerCantidadCarros() {
		List<VehiculoEntity> vehiculosAValidar = listarVehiculos();
		int contadorCarros = 0;

		for (VehiculoEntity vehiculo : vehiculosAValidar) {
			if (vehiculo.getTipo().equals(CARRO)) {
				contadorCarros += 1;

			}
		}

		return contadorCarros;
	}

	@Override
	public int obtenerCantidadMotos() {
		List<VehiculoEntity> vehiculosAValidar = listarVehiculos();
		int contadorMotos = 0;
		for (VehiculoEntity vehiculo : vehiculosAValidar) {
			if (vehiculo.getTipo().equals(MOTO)) {
				contadorMotos += 1;

			}
		}

		return contadorMotos;
	}

	@Override
	public int cobroTotalPorVehiculo(CeldaParqueo celda) {
		int valorTotal = 0;
		if (retirarVehiculo(celda.getVehiculo()).equals("retiro exitoso")) {
			celda.setHoraSalida(Calendar.getInstance());
			repositoriocelda.cambiarHoraSalida(celda);
			valorTotal = precioTotalPorVehiculo(celda);
		}
		return valorTotal;
	}

}
