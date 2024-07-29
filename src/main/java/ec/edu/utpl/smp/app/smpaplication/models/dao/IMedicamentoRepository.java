package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Medicamento;

@Repository
public interface IMedicamentoRepository extends JpaRepository<Medicamento, Integer> {

	public Medicamento findById(int id);

	@Query(value = "SELECT m FROM Medicamento m WHERE m.cantidad_Disponible>=1")
	public List<Medicamento> obtenerMedicamentoDisponible();

}