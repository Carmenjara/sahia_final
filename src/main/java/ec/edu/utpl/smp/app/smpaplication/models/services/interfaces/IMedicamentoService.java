package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Medicamento;

public interface IMedicamentoService {

	public void save(Medicamento medicamento);

	public Medicamento get(Integer id);

	public void delete(Integer id);

	public List<Medicamento> obtenerMedicamentoDisponible();

}