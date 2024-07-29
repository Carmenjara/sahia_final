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
@Table(name = "Especialidad")
public class Especialidad implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 255, nullable = false)
	private String descripcion;

	@Column(length = 10, nullable = false)
	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_creacion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_modificacion;

	@Column(length = 255, nullable = false)
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	public Especialidad() {
	}

	public Especialidad(int id, String descripcion, int estado, Date fecha_creacion, Date fecha_modificacion,
			String nombre, Usuario usuario) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estado = estado;
		this.fecha_creacion = fecha_creacion;
		this.fecha_modificacion = fecha_modificacion;
		this.nombre = nombre;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fecha_creacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fecha_creacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return fecha_modificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fecha_modificacion = fechaModificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static String obtenerPrefijoEsp(String especialidad) {
		String resultado = null;

		if (especialidad.length() >= 4) {
			resultado = especialidad.substring(0, 4).toUpperCase();
		} else {
			resultado = especialidad.toUpperCase();
		}
		return resultado;
	}

}
