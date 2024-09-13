package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IRecetaRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Receta;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IRecetaService;

@Service
public class RecetaServiceImpl implements IRecetaService {

	@Autowired
	private IRecetaRepository recetaRepository;

	@Override
	public void save(Receta receta) {
		recetaRepository.save(receta);

	}

	@Override
	public Receta get(Integer id) {
		return recetaRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		recetaRepository.deleteById(id);

	}

	@Override
	public List<Receta> getAllRecetas() {
		return recetaRepository.getAllRecetas();
	}

	@Override
	public List<Receta> getAllRecetaPorPaciente(int idPaciente) {
		return recetaRepository.getAllRecetaPorPaciente(idPaciente);
	}

}