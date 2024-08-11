
package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.Date;
import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.ConsultorioMedico;

public interface IConsultorioMedicoService {

	public void save(ConsultorioMedico consultorioMedico);

	public ConsultorioMedico findByMedicoId(Integer medicoId);

	public ConsultorioMedico findByMedicoIdEdit(int id);

	public int updateConsultorioId(int oldConsultorioId, int newConsultorioId, int medicoId, Date fecha);

	public List<ConsultorioMedico> findConsultoriosWithMedico();

}