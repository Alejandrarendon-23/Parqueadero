package persistencia.builder;

import dominio.Vehiculo;
import persistencia.entidad.VehiculoEntity;

public class VehiculoBuilder {
	
	private VehiculoBuilder() {}
	
	
	public static Vehiculo convertirADominio(VehiculoEntity vehiculoEntity) {
		Vehiculo vehiculo = null;
		if(vehiculoEntity != null) {
			vehiculo = new Vehiculo(vehiculoEntity.getTipo(), vehiculoEntity.getPlaca(), vehiculoEntity.getEstado());
		}
		return vehiculo;
	}
	
	public static VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setTipo(vehiculo.getTipo());
		vehiculoEntity.setPlaca(vehiculo.getPlaca());
		
		return vehiculoEntity;
	}

}
