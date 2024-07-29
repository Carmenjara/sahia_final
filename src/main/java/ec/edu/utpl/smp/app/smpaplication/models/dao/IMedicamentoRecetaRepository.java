package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicamentoReceta;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicamentoRecetaId;

@Repository
public interface IMedicamentoRecetaRepository extends JpaRepository<MedicamentoReceta, MedicamentoRecetaId> {

	public List<MedicamentoReceta> findByMedicamentoId(Integer id);
	
	public void deleteByMedicamentoId(Integer medicamentoId);

}