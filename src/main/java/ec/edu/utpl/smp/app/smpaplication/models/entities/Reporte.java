package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reporte")
public class Reporte implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 255, nullable = false)
	private String descripcion;

	// @Temporal(TemporalType.DATE)
	private Date fechaGeneracion;

	@ManyToOne
	@JoinColumn(name = "citaMedica_id")
	private CitaMedica citaMedica;

	public Reporte() {
	}

	public Reporte(int id, String descripcion, Date fechaGeneracion, CitaMedica citaMedica) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fechaGeneracion = fechaGeneracion;
		this.citaMedica = citaMedica;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public CitaMedica getCitaMedica() {
		return citaMedica;
	}

	public void setCitaMedica(CitaMedica citaMedica) {
		this.citaMedica = citaMedica;
	}

}
