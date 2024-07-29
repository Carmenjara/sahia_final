package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ConsultorioMedicoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "consultorio_id")
	private Integer consultorioid;

	@Column(name = "medico_id")
	private Integer medicoid;

	public ConsultorioMedicoId() {
	}

	public ConsultorioMedicoId(Integer consultorioid, Integer medicoid) {
		super();
		this.consultorioid = consultorioid;
		this.medicoid = medicoid;
	}

	public int getConsultorioId() {
		return consultorioid;
	}

	public void setConsultorioId(Integer consultorioid) {
		this.consultorioid = consultorioid;
	}

	public int getMedicoId() {
		return medicoid;
	}

	public void setMedicoId(Integer medicoid) {
		this.medicoid = medicoid;
	}

}