package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IMedicoHorarioRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Horario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoHorario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoHorarioId;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IMedicoHorarioService;

@Service
public class MedicoHorarioServiceImpl implements IMedicoHorarioService {

	@Autowired
	private IMedicoHorarioRepository medicohorarioRepository;

	@Override
	public Iterable<MedicoHorario> getAllMedicoHorarios() {
		return medicohorarioRepository.findAll();
	}

	@Override
	public void save(MedicoHorario medicoHorario) {
		medicohorarioRepository.save(medicoHorario);

	}

	@Override
	public MedicoHorario findById(MedicoHorarioId id) {
		return medicohorarioRepository.findById(id).get();
	}

	@Override
	public MedicoHorario findByMedicoIdAndHorarioId(int medicoId, int horarioId) {
		return medicohorarioRepository.findByMedicoIdAndHorarioId(medicoId, horarioId);
	}

	@Override
	public List<Horario> findHorariosByMedicoId(int medicoId) {
		return medicohorarioRepository.findHorariosByMedicoId(medicoId);
	}
	
	@Override
	public MedicoHorario findByHorarioId(int horarioId) {
		return medicohorarioRepository.findByHorarioId(horarioId);
	}

}