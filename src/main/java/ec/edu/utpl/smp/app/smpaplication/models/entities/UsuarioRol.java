package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario_Rol")
public class UsuarioRol implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsuarioRolId id;

	@ManyToOne
	@MapsId("usuarioId")
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@MapsId("rolId")
	@JoinColumn(name = "rol_id")
	private Roles rol;

	public UsuarioRol() {
	}

	public UsuarioRol(UsuarioRolId id, Usuario usuario, Roles rol) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.rol = rol;
	}

	public UsuarioRolId getId() {
		return id;
	}

	public void setId(UsuarioRolId id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

}