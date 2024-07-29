package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MedicoEspecialidadId implements Serializable {

	private static final long serialVersionUID = 1L;

	private int medicoId;
	private int especialidadId;

	public MedicoEspecialidadId() {
	}

	public MedicoEspecialidadId(int medicoId, int especialidadId) {
		super();
		this.medicoId = medicoId;
		this.especialidadId = especialidadId;
	}

	public int getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(int medicoId) {
		this.medicoId = medicoId;
	}

	public int getEspecialidadId() {
		return especialidadId;
	}

	public void setEspecialidadId(int especialidadId) {
		this.especialidadId = especialidadId;
	}
}
