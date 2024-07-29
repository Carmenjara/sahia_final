package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

	public Iterable<Usuario> getAllUsers();

	public boolean existeUsuario(String username);

	public void save(Usuario usuario);

	public void delete(Integer id);

	public Usuario findById(Integer id);
	
	public Iterable<Usuario> findAllMedicos();

	/*
	 * Cuando se trabaja con inyección de dependencias, se debe trabajar con
	 * interfaces para que el código pueda ser más mantenible y se pueda adaptar a
	 * cosas nuevas
	 */

}
