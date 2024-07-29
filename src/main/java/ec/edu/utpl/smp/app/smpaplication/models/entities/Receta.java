package ec.edu.utpl.smp.app.smpaplication.models.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Receta")
public class Receta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(nullable = false, length = 255)
	private String indicaciones;

	@Column(name = "duracion_Tratamiento", nullable = false, length = 30)
	private String duracionTratamiento;

	@Column(name = "frecuencia_Tratamiento", nullable = false, length = 30)
	private String frecuenciaTratamiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cita_medica_id", referencedColumnName = "id")
	private CitaMedica citaMedica;

	public Receta() {
	}

	public Receta(int id, Date fecha, String indicaciones, String duracionTratamiento, String frecuenciaTratamiento,
			CitaMedica citaMedica) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.indicaciones = indicaciones;
		this.duracionTratamiento = duracionTratamiento;
		this.frecuenciaTratamiento = frecuenciaTratamiento;
		this.citaMedica = citaMedica;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getIndicaciones() {
		return indicaciones;
	}

	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}

	public String getDuracionTratamiento() {
		return duracionTratamiento;
	}

	public void setDuracionTratamiento(String duracionTratamiento) {
		this.duracionTratamiento = duracionTratamiento;
	}

	public String getFrecuenciaTratamiento() {
		return frecuenciaTratamiento;
	}

	public void setFrecuenciaTratamiento(String frecuenciaTratamiento) {
		this.frecuenciaTratamiento = frecuenciaTratamiento;
	}

	public CitaMedica getCitaMedica() {
		return citaMedica;
	}

	public void setCitaMedica(CitaMedica citaMedica) {
		this.citaMedica = citaMedica;
	}
}
