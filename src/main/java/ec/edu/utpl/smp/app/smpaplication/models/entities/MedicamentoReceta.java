package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Medicamento_Receta")
public class MedicamentoReceta implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MedicamentoRecetaId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("medicamentoId")
	@JoinColumn(name = "medicamento_id", nullable = false)
	private Medicamento medicamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("recetaId")
	@JoinColumn(name = "receta_id", nullable = false)
	private Receta receta;

	public MedicamentoReceta() {
	}

	public MedicamentoReceta(MedicamentoRecetaId id, Medicamento medicamento, Receta receta) {
		this.id = id;
		this.medicamento = medicamento;
		this.receta = receta;
	}

	public MedicamentoRecetaId getId() {
		return id;
	}

	public void setId(MedicamentoRecetaId id) {
		this.id = id;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}
}
