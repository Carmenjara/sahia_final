package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IMedicamentoRecetaRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicamentoReceta;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IMedicamentoRecetaService;

@Service
public class MedicamentoRecetaServiceImpl implements IMedicamentoRecetaService {

	@Autowired
	private IMedicamentoRecetaRepository medicamentoRecetaRepository;

	@Override
	public void save(MedicamentoReceta medicamentoReceta) {
		medicamentoRecetaRepository.save(medicamentoReceta);

	}

	@Override
	public void deleteByMedicamentoId(Integer medicamentoId) {
		medicamentoRecetaRepository.deleteByMedicamentoId(medicamentoId);

	}

	@Override
	public List<MedicamentoReceta> findByMedicamentoId(Integer id) {
		return medicamentoRecetaRepository.findByMedicamentoId(id);
	}

}