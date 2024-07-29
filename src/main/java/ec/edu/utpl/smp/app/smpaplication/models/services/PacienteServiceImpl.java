package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IPacienteRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Paciente;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService {

	@Autowired
	private IPacienteRepository pacienteRepository;

	@Override
	public Iterable<Paciente> getAllPersonas() {
		return pacienteRepository.findAll();
	}

	@Override
	public void save(Paciente paciente) {
		int edad=calcularEdad(paciente.getPersona().getFecha_nacimiento(), LocalDate.now());
		paciente.setEdad(edad);
		pacienteRepository.save(paciente);
	}

	@Override
	public Paciente get(Integer id) {
		return pacienteRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		pacienteRepository.deleteById(id);

	}

	@Override
	public Optional<Paciente> findByPersonaIdentificacion(String identificacion) {
		return pacienteRepository.findByPersonaIdentificacion(identificacion);
	}

	@Override
	public Paciente getPacienteByUsuario(int idUsuario) {
		return pacienteRepository.getPacienteByUsuario(idUsuario);
	}

	private int calcularEdad(LocalDate fechaNacimiento, LocalDate fechaActual) {
	    return Period.between(fechaNacimiento, fechaActual).getYears();
	}
}
