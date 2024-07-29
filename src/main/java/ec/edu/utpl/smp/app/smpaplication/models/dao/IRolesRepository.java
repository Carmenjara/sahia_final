package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Roles;

public interface IRolesRepository extends JpaRepository<Roles, Integer> {

	@Query("SELECT r.id, r.nombre FROM Roles r")
	public List<Roles> findAllRoles();

	@Query("SELECT r.id.rolId FROM UsuarioRol r WHERE r.id.usuarioId = ?1")
	public int findIdRol(int id);

}
