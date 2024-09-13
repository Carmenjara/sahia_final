package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Receta;

@Repository
public interface IRecetaRepository extends JpaRepository<Receta, Integer> {

	// Recetas de todos los pacientes para Administrador y Médico
	@Query("SELECT r FROM Receta r")
	public List<Receta> getAllRecetas();

	// Recetas por Paciente
	@Query(value = "SELECT r.* FROM Receta r JOIN Cita_Medica cm ON r.cita_medica_id = cm.id WHERE cm.paciente_id =:idPaciente", nativeQuery = true)
	public List<Receta> getAllRecetaPorPaciente(int idPaciente);

}