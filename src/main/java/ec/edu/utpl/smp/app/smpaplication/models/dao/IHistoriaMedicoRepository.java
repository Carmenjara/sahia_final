package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.HistorialMedico;

@Repository
public interface IHistoriaMedicoRepository extends JpaRepository<HistorialMedico, Integer> {

	// Historial General para Administrador y Médico
	@Query("SELECT hm FROM HistorialMedico hm")
	public List<HistorialMedico> getAllHistorialMedico();

	// Historial de Paciente
	@Query(value = "SELECT hm.* FROM Historia_Medico hm, Cita_Medica cm where hm.cita_medica_id=cm.id and cm.paciente_id=:idPaciente", nativeQuery = true)
	public List<HistorialMedico> getAllHistorialMedicoPorPaciente(int idPaciente);

}