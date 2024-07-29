package ec.edu.utpl.smp.app.smpaplication.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioRolId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "usuario_id")
	private Integer usuarioId;

	@Column(name = "rol_id")
	private Integer rolId;

	public UsuarioRolId() {
	}

	public UsuarioRolId(Integer usuarioId, Integer rolId) {
		super();
		this.usuarioId = usuarioId;
		this.rolId = rolId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

}