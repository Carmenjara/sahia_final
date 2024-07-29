package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Horario")
public class Horario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fecha;

	@Column(nullable = false)
	private java.sql.Time hora;

	@Column(length = 50)
	private String tipo;

	@Column(nullable = false)
	private int estado;

	public Horario() {
	}

	public Horario(int id, Time hora) {
		super();
		this.id = id;
		this.hora = hora;
	}

	public Horario(int id, Date fecha, Time hora, String tipo, int estado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.tipo = tipo;
		this.estado = estado;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public java.sql.Time getHora() {
		return hora;
	}

	public void setHora(java.sql.Time hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Horario [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", tipo=" + tipo + ", estado=" + estado
				+ "]";
	}

}
