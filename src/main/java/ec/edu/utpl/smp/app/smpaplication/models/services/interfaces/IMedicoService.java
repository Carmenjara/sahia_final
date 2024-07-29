package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Medico;

public interface IMedicoService {

	public Iterable<Medico> getAllMedicos();

	public void save(Medico medico);

	public Medico get(Integer id);

	public void delete(Integer id);

	List<Medico> findMedicosByEspecialidadId(int especialidadId);
	
	public Medico findByUsuarioId(Integer usuarioId);

}