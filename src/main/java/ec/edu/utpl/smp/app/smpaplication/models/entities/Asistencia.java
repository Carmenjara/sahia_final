package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Asistencia")
public class Asistencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "dia_Inicio", nullable = false)
	private LocalDateTime dia_Inicio;

	@Column(name = "dia_Fin", nullable = false)
	private LocalDateTime dia_Fin;

	@Column(length = 255)
	private String comentario;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Asistencia() {
	}

	public Asistencia(int id, LocalDateTime dia_Inicio, LocalDateTime dia_Fin, String comentario, Usuario usuario) {
		super();
		this.id = id;
		this.dia_Inicio = dia_Inicio;
		this.dia_Fin = dia_Fin;
		this.comentario = comentario;
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDia_Inicio() {
		return dia_Inicio;
	}

	public void setDia_Inicio(LocalDateTime dia_Inicio) {
		this.dia_Inicio = dia_Inicio;
	}

	public LocalDateTime getDia_Fin() {
		return dia_Fin;
	}

	public void setDia_Fin(LocalDateTime dia_Fin) {
		this.dia_Fin = dia_Fin;
	}

}
