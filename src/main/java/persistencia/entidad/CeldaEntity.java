package persistencia.entidad;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity(name = "Celda")
@NamedQueries({
@NamedQuery(name = "Celda.findByPlaca", query = "SELECT celda FROM Celda celda WHERE celda.vehiculo.placa = :placa"),
@NamedQuery(name = "Celda.findAll", query=("SELECT celda FROM Celda celda"))
})
public class CeldaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Calendar horaIngreso;

	@Column(nullable = true)
	private Calendar horaSalida;

	@OneToOne
	@JoinColumn(nullable = false)
	private VehiculoEntity vehiculo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(Calendar horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public Calendar getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Calendar horaSalida) {
		this.horaSalida = horaSalida;
	}

	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
	}

}
