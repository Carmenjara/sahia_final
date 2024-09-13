package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IHistoriaMedicoRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.HistorialMedico;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IHistoriaMedicoService;

@Service
public class HistoriaMedicoServiceImpl implements IHistoriaMedicoService {

	@Autowired
	private IHistoriaMedicoRepository historiMedicoRepository;

	@Override
	public void save(HistorialMedico historiaMedico) {
		historiMedicoRepository.save(historiaMedico);

	}

	@Override
	public HistorialMedico get(Integer id) {
		return historiMedicoRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		historiMedicoRepository.deleteById(id);

	}

	@Override
	public List<HistorialMedico> getAllHistorialMedico() {
		return historiMedicoRepository.getAllHistorialMedico();
	}

	@Override
	public List<HistorialMedico> getAllHistorialMedicoPorPaciente(int idPaciente) {
		return historiMedicoRepository.getAllHistorialMedicoPorPaciente(idPaciente);
	}

}