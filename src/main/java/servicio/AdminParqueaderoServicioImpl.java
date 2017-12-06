package servicio;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;

import dominio.Vehiculo;
import dominio.repositorio.RepositorioVehiculo;
import persistencia.sistema.SistemaPersistencia;
import dominio.CeldaParqueo;

public class AdminParqueaderoServicioImpl implements AdminParqueaderoServicio {
	
	private static final int MAX_CARROS = 20;
	private static final int MAX_MOTOS = 10;
	private static final float VALOR_HORA_CARRO = 1000;
	private static final float VALOR_DIA_CARRO = 8000;
	private static final float VALOR_HORA_MOTO = 500;
	private static final float VALOR_DIA_MOTO = 8000;
	private static final int MAX_CILINDRAJE = 500;
	private RepositorioVehiculo repositoriovehiculo;
	private Vehiculo vehiculo;
	
	public AdminParqueaderoServicioImpl() {
		SistemaPersistencia sistemapersistencia = new SistemaPersistencia();
		this.repositoriovehiculo = sistemapersistencia.obtenerRepositorioVehiculo();
	}
	
	@Override
	public List<Vehiculo> listarVehiculos() {
		
		return null;
	}
	@Override
	public boolean esMayorAlCilindrajePermitido(int cilindraje) {
		
		if ( cilindraje > MAX_CILINDRAJE ) {
			
			return true;
		}		
		return false;		
	}
	
	@Override
	public int calcularValorTotalPorTiempo(CeldaParqueo celda) {
		Calendar fechaIngreso = celda.getHoraIngreso();
		Calendar fechaSalida = celda.getHoraSalida();
		long fechaDiferencia =  (fechaSalida.getTimeInMillis() - fechaIngreso.getTimeInMillis());
		
		int numHoras = (int) (fechaDiferencia/(1000*60*60));
		
		
		
		

	
		
		return 0;
	}

    @Override
	public boolean esPermitidoIngresoPorPlaca(String placa) {
    	Calendar now = Calendar.getInstance();
    	if ((placa.charAt(0)== 'A') && ((now.get((Calendar.DAY_OF_WEEK) - 1)!=1) || (now.get((Calendar.DAY_OF_WEEK) - 1)!= 2))){
			System.err.println("No está autorizado para ingresar al parqueadero");
			return false;
		}
		return true;
	}
    public void ingresarVehiculo(Vehiculo vehiculo) {
    	
		repositoriovehiculo.agregar(vehiculo);
	}
    public void retirarVehiculo(Vehiculo vehiculo) {
    	
    	repositoriovehiculo.actualizarVehiculo(vehiculo);
    }




	
    
    }


	
