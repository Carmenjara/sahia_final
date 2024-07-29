package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MedicamentoRecetaId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "medicamento_id")
	private int medicamentoId;

	@Column(name = "receta_id")
	private int recetaId;

	public MedicamentoRecetaId() {
	}

	public MedicamentoRecetaId(int medicamentoId, int recetaId) {
		this.medicamentoId = medicamentoId;
		this.recetaId = recetaId;
	}

	public int getMedicamentoId() {
		return medicamentoId;
	}

	public void setMedicamentoId(int medicamentoId) {
		this.medicamentoId = medicamentoId;
	}

	public int getRecetaId() {
		return recetaId;
	}

	public void setRecetaId(int recetaId) {
		this.recetaId = recetaId;
	}

}
