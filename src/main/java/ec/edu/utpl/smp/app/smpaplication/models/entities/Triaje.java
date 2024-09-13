package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Triaje")
public class Triaje implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "frecuencia_cardiaca", nullable = false)
	private int frecuenciaCardiaca;

	@Column(name = "frecuencia_respiratoria", nullable = false)
	private int frecuenciaRespiratoria;

	@Column(name = "nivel_saturacion_oxigeno", nullable = false)
	private int nivelSaturacionOxigeno;

	@Column(name = "presion_arterial", nullable = false)
	private int presionArterial;

	@Column(name = "temperatura_corporal", nullable = false)
	private int temperaturaCorporal;

	@Column(name = "estado", length = 10, nullable = false)
	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion")
	private Date fechaActualizacion;

	@ManyToOne
	@JoinColumn(name = "paciente_id", referencedColumnName = "id")
	private Paciente paciente;

	public Triaje() {
	}

	public Triaje(Integer id, int frecuenciaCardiaca, int frecuenciaRespiratoria, int nivelSaturacionOxigeno,
			int presionArterial, int temperaturaCorporal, String estado, Date fechaCreacion, Date fechaActualizacion,
			Paciente paciente) {
		super();
		this.id = id;
		this.frecuenciaCardiaca = frecuenciaCardiaca;
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
		this.nivelSaturacionOxigeno = nivelSaturacionOxigeno;
		this.presionArterial = presionArterial;
		this.temperaturaCorporal = temperaturaCorporal;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.paciente = paciente;
	}

	// Getters y setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getFrecuenciaCardiaca() {
		return frecuenciaCardiaca;
	}

	public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
		this.frecuenciaCardiaca = frecuenciaCardiaca;
	}

	public int getFrecuenciaRespiratoria() {
		return frecuenciaRespiratoria;
	}

	public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
	}

	public int getNivelSaturacionOxigeno() {
		return nivelSaturacionOxigeno;
	}

	public void setNivelSaturacionOxigeno(int nivelSaturacionOxigeno) {
		this.nivelSaturacionOxigeno = nivelSaturacionOxigeno;
	}

	public int getPresionArterial() {
		return presionArterial;
	}

	public void setPresionArterial(int presionArterial) {
		this.presionArterial = presionArterial;
	}

	public int getTemperaturaCorporal() {
		return temperaturaCorporal;
	}

	public void setTemperaturaCorporal(int temperaturaCorporal) {
		this.temperaturaCorporal = temperaturaCorporal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

}