package ec.edu.utpl.smp.app.smpaplication.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	// public Optional<Usuario> findByUsername(String username);

	public Usuario findByUsername(String username);

	@Query("SELECT CASE WHEN COUNT(u.username) > 0 THEN true ELSE false END FROM Usuario u WHERE u.username =?1")
	boolean existeUsuario(String username);

	public Usuario findById(int id);
	
	@Query(value = ("select u.* from Usuario_Rol ur, Rol r, Usuario u, Medico m where ur.usuario_id=u.id and ur.rol_id=r.id and r.nombre='MEDICO'and m.persona_id=u.persona_id"), nativeQuery = true)
	public Iterable<Usuario> findAllMedicos();

	// @Query("select r.nombre from Roles r join r.usuario u where u.username =
	// :username")
	// List<String> findRolesByUsername(@Param("username") String username);

}
