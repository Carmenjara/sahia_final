package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Horario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoHorario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoHorarioId;

public interface IMedicoHorarioService {

	public Iterable<MedicoHorario> getAllMedicoHorarios();

	public void save(MedicoHorario medicoHorario);

	public MedicoHorario findById(MedicoHorarioId id);

	public MedicoHorario findByMedicoIdAndHorarioId(int medicoId, int horarioId);
	
	public MedicoHorario findByHorarioId(int horarioId);

	List<Horario> findHorariosByMedicoId(int medicoId);

	/*
	 * Cuando se trabaja con inyección de dependencias, se debe trabajar con
	 * interfaces para que el código pueda ser más mantenible y se pueda adaptar a
	 * cosas nuevas
	 */

}