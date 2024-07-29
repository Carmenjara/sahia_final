package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
//@UniqueConstraint permite evitar duplicidad a nivel de base de username
@Table(name = "Usuario", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username", nullable = false, length = 100, unique = true)
	private String username;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "enabled", nullable = false)
	private int enabled;

	@ManyToOne
	@JoinColumn(name = "persona_id", nullable = false)
	private Persona persona;

	@OneToMany(mappedBy = "usuario")
	private List<Asistencia> asistencias;

	public Usuario() {
	}

	public Usuario(int id, String username, String password, int enabled, Persona persona,
			List<Asistencia> asistencias) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.persona = persona;
		this.asistencias = asistencias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.toLowerCase();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Asistencia> getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	private static final long serialVersionUID = 1L;

}