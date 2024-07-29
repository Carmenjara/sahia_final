package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.Optional;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Paciente;

public interface IPacienteService {

	public Iterable<Paciente> getAllPersonas();

	public void save(Paciente persona);

	public Paciente get(Integer id);

	public void delete(Integer id);

	Optional<Paciente> findByPersonaIdentificacion(String identificacion);

	public Paciente getPacienteByUsuario(int idUsuario);
	/*
	 * Cuando se trabaja con inyección de dependencias, se debe trabajar con
	 * interfaces para que el código pueda ser más mantenible y se pueda adaptar a
	 * cosas nuevas
	 */

}