package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.HistorialMedico;

public interface IHistoriaMedicoService {

	public void save(HistorialMedico historiaMedico);

	public HistorialMedico get(Integer id);

	public void delete(Integer id);

	public List<HistorialMedico> getAllHistorialMedico();
	
	public List<HistorialMedico> getAllHistorialMedicoPorPaciente(int idPaciente);

}