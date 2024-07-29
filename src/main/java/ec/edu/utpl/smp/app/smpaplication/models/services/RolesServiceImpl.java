package ec.edu.utpl.smp.app.smpaplication.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IRolesRepository;

@Service
public class RolesServiceImpl {

	@Autowired
	IRolesRepository rolesRepository;

	/*
	 * @Override public void save(Roles roles) { rolesRepository.save(roles); }
	 * 
	 * @Override public List<String> getAllRoles() { return
	 * rolesRepository.getAllRoles(); }
	 */

}
