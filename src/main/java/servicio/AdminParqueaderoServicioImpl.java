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

		if(cilindraje > MAX_CILINDRAJE) {

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
		int minutos = (int) ( (fechaDiferencia % (1000 * 60 * 60)) / (1000 * 60));
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
		if (minutos >0) {
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
		if ((placa.charAt(0) == 'A')
				&& ((now.get((Calendar.DAY_OF_WEEK)) != 1) || (now.get((Calendar.DAY_OF_WEEK)) != 2))) {
			System.err.println("No está autorizado para ingresar al parqueadero");
			return false;
		}
		return true;
	}

	public String ingresarVehiculo(Vehiculo vehiculo) {
		String mensaje = "No se permite su ingreso al parqueadero";
		if (vehiculo.getTipo().equals("carro") && esPermitidoIngresoPorPlaca((vehiculo.getPlaca())) == true
				&& obtenerCantidadCarros() <= MAX_CARROS && vehiculo.getEstado() == false) {
			vehiculo.setEstado(true);

			repositoriovehiculo.agregar(VehiculoBuilder.convertirAEntity(vehiculo));
			CeldaEntity celdaEntity = new CeldaEntity();
			VehiculoEntity vehiculoEntity = repositoriovehiculo.obtenerPorPlaca(vehiculo.getPlaca());

			celdaEntity.setHoraIngreso(Calendar.getInstance());
			celdaEntity.setVehiculo(vehiculoEntity);

			repositoriocelda.agregarCelda(celdaEntity);
			mensaje = ("Está autorizado para ingresar al parqueadero");

		} else if (vehiculo.getTipo().equals("moto") && esPermitidoIngresoPorPlaca((vehiculo.getPlaca())) == true
				&& obtenerCantidadMotos() <= MAX_MOTOS && vehiculo.getEstado() == false) {
			vehiculo.setEstado(true);

			repositoriovehiculo.agregar(VehiculoBuilder.convertirAEntity(vehiculo));
			CeldaEntity celdaEntity = new CeldaEntity();
			VehiculoEntity vehiculoEntity = repositoriovehiculo.obtenerPorPlaca(vehiculo.getPlaca());

			celdaEntity.setHoraIngreso(Calendar.getInstance());
			celdaEntity.setVehiculo(vehiculoEntity);

			repositoriocelda.agregarCelda(celdaEntity);
			mensaje = ("Está autorizado para ingresar al parqueadero");
		}
		return mensaje;
	}

	@Override
	public String retirarVehiculo(Vehiculo vehiculo) {
		String mensaje = "No es posible retirar el vehiculo";
		if (vehiculo.getEstado() == true) {
			vehiculo.setEstado(false);

			repositoriovehiculo.actualizarVehiculo(VehiculoBuilder.convertirAEntity(vehiculo));
			mensaje = "retiro exitoso";
		}
		return mensaje;

	}

	@Override
	public int precioTotalPorVehiculo(CeldaParqueo celda) {
		int precioTotal = 0;
		if (celda.getVehiculo().getTipo().equals("moto")
				&& esMayorAlCilindrajePermitido(((Moto) celda.getVehiculo()).getCilindraje()) == true) {
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
			if (vehiculo.getTipo().equals("carro")) {
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
			if (vehiculo.getTipo().equals("moto")) {
				contadorMotos += 1;

			}
		}

		return contadorMotos;
	}

	@Override
	public int cobroTotalPorVehiculo(CeldaParqueo celda) {
		int valorTotal = 0;
		if (retirarVehiculo(celda.getVehiculo()).equals("retiro exitoso")) {
			valorTotal = precioTotalPorVehiculo(celda);
		}
		return valorTotal;
	}

}
