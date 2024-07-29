package ec.edu.utpl.smp.app.smpaplication.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Historia_Medico")
public class HistorialMedico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "diagnostico", nullable = false)
	private String diagnostico;

	@Column(name = "examenes", nullable = false, length = 255)
	private String examenes;

	@Column(name = "proximo_control", nullable = false)
	private int proximoControl;

	@ManyToOne
	@JoinColumn(name = "cita_medica_id")
	private CitaMedica citaMedica;

	public HistorialMedico() {
	}

	public HistorialMedico(int id, String diagnostico, String examenes, int proximoControl, CitaMedica citaMedica) {
		super();
		this.id = id;
		this.diagnostico = diagnostico;
		this.examenes = examenes;
		this.proximoControl = proximoControl;
		this.citaMedica = citaMedica;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getExamenes() {
		return examenes;
	}

	public void setExamenes(String examenes) {
		this.examenes = examenes;
	}

	public int getProximoControl() {
		return proximoControl;
	}

	public void setProximoControl(int proximoControl) {
		this.proximoControl = proximoControl;
	}

	public CitaMedica getCitaMedica() {
		return citaMedica;
	}

	public void setCitaMedica(CitaMedica citaMedica) {
		this.citaMedica = citaMedica;
	}
}
