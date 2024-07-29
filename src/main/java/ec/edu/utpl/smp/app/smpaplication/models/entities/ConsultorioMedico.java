package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Consultorio_Medico")
public class ConsultorioMedico implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConsultorioMedicoId id;

	@ManyToOne
	@MapsId("consultorioid")
	@JoinColumn(name = "consultorio_id")
	private Consultorios consultorio;

	@ManyToOne
	@MapsId("medicoid")
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@Column(name = "fecha", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	public ConsultorioMedico() {
	}

	public ConsultorioMedico(ConsultorioMedicoId id, Consultorios consultorio, Medico medico, Date fecha) {
		super();
		this.id = id;
		this.consultorio = consultorio;
		this.medico = medico;
		this.fecha = fecha;
	}

	public ConsultorioMedicoId getId() {
		return id;
	}

	public void setId(ConsultorioMedicoId id) {
		this.id = id;
	}

	public Consultorios getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(Consultorios consultorio) {
		this.consultorio = consultorio;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}