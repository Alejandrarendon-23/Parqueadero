package persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "Vehiculo")
@NamedQueries({
		@NamedQuery(name = "Vehiculo.findByPlaca", query = "SELECT vehiculo FROM Vehiculo vehiculo WHERE vehiculo.placa = :placa"),
		@NamedQuery(name = "Vehiculo.findAll", query=("SELECT vehiculo FROM Vehiculo vehiculo"))
})
public class VehiculoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String tipo;

	@Column(nullable = false)
	private String placa;

	@Column(nullable = false)
	private boolean estado;
	
	@Column
	private int cilindraje;

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
