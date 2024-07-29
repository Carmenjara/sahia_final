package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Medico_Especialidad")
public class MedicoEspecialidad implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MedicoEspecialidadId id;

	@ManyToOne
	@MapsId("medicoId")
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@ManyToOne
	@MapsId("especialidadId")
	@JoinColumn(name = "especialidad_id")
	private Especialidad especialidad;

	public MedicoEspecialidad() {
	}

	public MedicoEspecialidad(MedicoEspecialidadId id, Medico medico, Especialidad especialidad) {
		super();
		this.id = id;
		this.medico = medico;
		this.especialidad = especialidad;
	}

	public MedicoEspecialidadId getId() {
		return id;
	}

	public void setId(MedicoEspecialidadId id) {
		this.id = id;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

}
