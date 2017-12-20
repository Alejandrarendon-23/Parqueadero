package persistencia.builder;

import dominio.Carro;
import dominio.Moto;
import dominio.Vehiculo;
import persistencia.entidad.VehiculoEntity;

public class VehiculoBuilder {

	private VehiculoBuilder() {
	}

	public static Vehiculo convertirADominio(VehiculoEntity vehiculoEntity) {
		Vehiculo vehiculo = null;
		if (vehiculoEntity != null) {
			if (vehiculoEntity.getTipo().equals("moto")) {
				vehiculo = new Moto(vehiculoEntity.getTipo(), vehiculoEntity.getPlaca(), vehiculoEntity.getEstado(),
						vehiculoEntity.getCilindraje());

			} else {
				vehiculo = new Carro(vehiculoEntity.getTipo(), vehiculoEntity.getPlaca(), vehiculoEntity.getEstado());
			}
		}
		return vehiculo;
	}

	public static VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setEstado(vehiculo.getEstado());
		vehiculoEntity.setTipo(vehiculo.getTipo());
		vehiculoEntity.setPlaca(vehiculo.getPlaca());

		if (vehiculoEntity.getTipo().equals("moto")) {
			Moto moto = (Moto) vehiculo;

			vehiculoEntity.setCilindraje(moto.getCilindraje());
		}

		return vehiculoEntity;
	}

}
