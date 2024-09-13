package ec.edu.utpl.smp.app.smpaplication.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.ITriajeRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Triaje;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.ITriajeService;

@Service
public class TriajeServiceImpl implements ITriajeService {

	@Autowired
	private ITriajeRepository triajeRepository;

	@Override
	public Iterable<Triaje> getAllTriajes() {
		return triajeRepository.findAll();
	}

	@Override
	public void save(Triaje triaje) {
		triajeRepository.save(triaje);

	}

	@Override
	public Triaje get(Integer id) {
		return triajeRepository.getOne(id);
	}

	@Override
	public void delete(Integer id) {
		triajeRepository.deleteById(id);

	}

}