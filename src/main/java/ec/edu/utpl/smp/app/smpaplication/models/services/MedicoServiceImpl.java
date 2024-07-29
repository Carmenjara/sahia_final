package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IMedicoRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Medico;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService {

	@Autowired
	private IMedicoRepository medicoRepository;

	@Override
	public Medico get(Integer id) {
		return medicoRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		medicoRepository.deleteById(id);

	}

	@Override
	public Iterable<Medico> getAllMedicos() {
		return medicoRepository.findAll();
	}

	@Override
	public void save(Medico medico) {
		medicoRepository.save(medico);

	}

	@Override
	public List<Medico> findMedicosByEspecialidadId(int especialidadId) {
		return medicoRepository.findMedicosByEspecialidadId(especialidadId);
	}
	
	@Override
	public Medico findByUsuarioId(Integer usuarioId) {
		return medicoRepository.findByUsuarioId(usuarioId);
	}

}