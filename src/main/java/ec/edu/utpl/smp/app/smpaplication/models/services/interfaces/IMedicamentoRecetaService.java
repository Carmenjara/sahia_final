package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicamentoReceta;

public interface IMedicamentoRecetaService {

	public void save(MedicamentoReceta medicamentoReceta);

	public void deleteByMedicamentoId(Integer medicamentoId);

	public List<MedicamentoReceta> findByMedicamentoId(Integer id);
	/*
	 * Cuando se trabaja con inyección de dependencias, se debe trabajar con
	 * interfaces para que el código pueda ser más mantenible y se pueda adaptar a
	 * cosas nuevas
	 */

}