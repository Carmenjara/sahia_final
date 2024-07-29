package ec.edu.utpl.smp.app.smpaplication.models.services;

import org.springframework.beans.factory.annotation.Autowired;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IUsuarioRepository;

public class UserDetailsServiceImpl {

	@Autowired
	IUsuarioRepository iUsuarioRepository;

}
