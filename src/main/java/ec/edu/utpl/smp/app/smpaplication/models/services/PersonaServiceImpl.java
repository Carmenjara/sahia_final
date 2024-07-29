package ec.edu.utpl.smp.app.smpaplication.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IPersonaRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Persona;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaRepository PersonaRepository;

	@Override
	public Iterable<Persona> getAllPersonas() {
		return PersonaRepository.findAll();
	}

	@Override
	public void save(Persona persona) {
		PersonaRepository.save(persona);

	}

	@Override
	public Persona get(Integer id) {
		return PersonaRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		PersonaRepository.deleteById(id);

	}

}