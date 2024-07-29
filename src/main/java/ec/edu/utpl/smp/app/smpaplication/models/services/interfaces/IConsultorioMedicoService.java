
package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.Date;

import ec.edu.utpl.smp.app.smpaplication.models.entities.ConsultorioMedico;

public interface IConsultorioMedicoService {

	public void save(ConsultorioMedico consultorioMedico);

	public ConsultorioMedico findByMedicoId(Integer medicoId);
	
	public ConsultorioMedico findByMedicoIdEdit(int id);
	
	public int updateConsultorioId(int oldConsultorioId, int newConsultorioId, int medicoId, Date fecha);

	// public void delete(Integer id);

	/*
	 * Cuando se trabaja con inyección de dependencias, se debe trabajar con
	 * interfaces para que el código pueda ser más mantenible y se pueda adaptar a
	 * cosas nuevas
	 */

}