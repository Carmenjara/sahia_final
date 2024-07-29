package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ec.edu.utpl.smp.app.smpaplication.models.entities.UsuarioRol;
import ec.edu.utpl.smp.app.smpaplication.models.entities.UsuarioRolId;

public interface IUsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {

	UsuarioRol findByUsuarioIdAndRolId(int usuarioId, int rolId);

	@Query("SELECT r FROM UsuarioRol r WHERE r.id.usuarioId = ?1")
	public UsuarioRol findByIdUsuarioRol(int id);

	public List<UsuarioRol> findByUsuarioId(int id);
}
