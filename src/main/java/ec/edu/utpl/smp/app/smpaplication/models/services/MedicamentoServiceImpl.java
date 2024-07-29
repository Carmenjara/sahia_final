package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IMedicamentoRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Medicamento;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IMedicamentoService;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService {

	@Autowired
	private IMedicamentoRepository medicamentoRepository;

	@Override
	public void save(Medicamento medicamento) {
		medicamentoRepository.save(medicamento);

	}

	@Override
	public Medicamento get(Integer id) {
		return medicamentoRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		medicamentoRepository.deleteById(id);

	}

	@Override
	public List<Medicamento> obtenerMedicamentoDisponible() {
		return medicamentoRepository.obtenerMedicamentoDisponible();
	}

}