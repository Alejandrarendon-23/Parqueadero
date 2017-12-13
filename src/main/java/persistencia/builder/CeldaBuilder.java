package persistencia.builder;
import dominio.CeldaParqueo;
import persistencia.entidad.CeldaEntity;


public class CeldaBuilder {
	
private CeldaBuilder() {}
	
	
	public static CeldaParqueo convertirADominio(CeldaEntity celdaEntity) {
		CeldaParqueo celda = null;
		if(celdaEntity != null) {
			celda = new CeldaParqueo(VehiculoBuilder.convertirADominio(celdaEntity.getVehiculo()), celdaEntity.getHoraIngreso(), celdaEntity.getHoraSalida());
		}
		return celda;
	}
	
	public static CeldaEntity convertirAEntity(CeldaParqueo celda) {
		CeldaEntity celdaEntity = new CeldaEntity();
		celdaEntity.setVehiculo(VehiculoBuilder.convertirAEntity(celda.getVehiculo()));
		celdaEntity.setHoraIngreso(celda.getHoraIngreso());
		celdaEntity.setHoraSalida(celda.getHoraSalida());
		
		return celdaEntity;
	}

}
