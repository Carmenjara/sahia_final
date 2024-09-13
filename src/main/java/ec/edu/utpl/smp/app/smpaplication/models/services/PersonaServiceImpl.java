package ec.edu.utpl.smp.app.smpaplication.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IPersonaRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Persona;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaRepository personaRepository;

	@Override
	public Iterable<Persona> getAllPersonas() {
		return personaRepository.findAll();
	}

	@Override
	public void save(Persona persona) {
		personaRepository.save(persona);

	}

	@Override
	public Persona get(Integer id) {
		return personaRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		personaRepository.deleteById(id);

	}

	@Override
	public Persona findByIdP(int id) {
		return personaRepository.findByIdP(id);
	}

}