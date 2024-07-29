package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Medico_Horario")
public class MedicoHorario implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MedicoHorarioId id;

	@ManyToOne
	@MapsId("medicoId")
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@ManyToOne
	@MapsId("horarioId")
	@JoinColumn(name = "horario_id")
	private Horario horario;

	public MedicoHorario() {
	}

	public MedicoHorario(MedicoHorarioId id, Medico medico, Horario horario) {
		super();
		this.id = id;
		this.medico = medico;
		this.horario = horario;
	}

	public MedicoHorarioId getId() {
		return id;
	}

	public void setId(MedicoHorarioId id) {
		this.id = id;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}
}
