package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MedicoHorarioId implements Serializable {

	private static final long serialVersionUID = 1L;

	private int medicoId;
	private int horarioId;

	public MedicoHorarioId() {
	}

	public MedicoHorarioId(int medicoId, int horarioId) {
		super();
		this.medicoId = medicoId;
		this.horarioId = horarioId;
	}

	public int getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(int medicoId) {
		this.medicoId = medicoId;
	}

	public int getHorarioId() {
		return horarioId;
	}

	public void setHorarioId(int horarioId) {
		this.horarioId = horarioId;
	}

}
