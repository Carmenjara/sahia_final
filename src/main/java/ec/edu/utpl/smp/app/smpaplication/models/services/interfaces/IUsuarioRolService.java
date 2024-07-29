package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.UsuarioRol;
import ec.edu.utpl.smp.app.smpaplication.models.entities.UsuarioRolId;

public interface IUsuarioRolService {

	public Iterable<UsuarioRol> getAllUsersRol();

	public void save(UsuarioRol usuarioRol);

	public void delete(Integer id);

	public UsuarioRol findById(UsuarioRolId id);

	public UsuarioRol findByIdUsuarioRol(int id);

	public UsuarioRol findByUsuarioIdAndRolId(int usuarioId, int rolId);
	
	public List<UsuarioRol> findByUsuarioId(int id);

	/*
	 * Cuando se trabaja con inyección de dependencias, se debe trabajar con
	 * interfaces para que el código pueda ser más mantenible y se pueda adaptar a
	 * cosas nuevas
	 */

}