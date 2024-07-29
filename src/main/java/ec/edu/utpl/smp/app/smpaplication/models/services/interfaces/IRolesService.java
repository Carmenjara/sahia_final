package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Roles;

public interface IRolesService {

	public void save(Roles roles);

	public List<Roles> findAllRoles();

	public Roles findById(int id);

	public int findIdRol(int id);

}
