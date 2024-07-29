package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.ICitaMedicaRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.CitaMedica;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.ICitaMedicaService;

@Service
public class CitaMedicaServiceImpl implements ICitaMedicaService {

	@Autowired
	private ICitaMedicaRepository citaMedicaRepository;

	@Override
	public Iterable<CitaMedica> getAllCitas() {
		return citaMedicaRepository.findAll();
	}

	@Override
	public void save(CitaMedica citaMedica) {
		citaMedicaRepository.save(citaMedica);
	}

	@Override
	public CitaMedica get(Integer id) {
		return citaMedicaRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		citaMedicaRepository.deleteById(id);

	}

	@Override
	public List<CitaMedica> getCitasPorMedicoDiarias(int medicoId) {
		return citaMedicaRepository.getCitasPorMedicoDiarias(medicoId);
	}

	@Override
	public List<CitaMedica> getCitasPorMedicoSemanales(int medicoId) {
		return citaMedicaRepository.getCitasPorMedicoSemanales(medicoId);
	}

	@Override
	public List<CitaMedica> getCitasPorMedicoMensuales(int medicoId) {
		return citaMedicaRepository.getCitasPorMedicoMensuales(medicoId);
	}

	@Override
	public List<CitaMedica> getCitasPorPaciente(int pacienteId) {
		return citaMedicaRepository.getCitasPorPaciente(pacienteId);
	}

	@Override
	public int numeroCitasPorDia(int pacienteId) {
		return citaMedicaRepository.numeroCitasPorDia(pacienteId);
	}

}
