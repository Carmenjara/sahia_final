package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.CitaMedica;

@Repository
public interface ICitaMedicaRepository extends JpaRepository<CitaMedica, Integer> {

	// Se listan solo las citas que estan por atender
	@Query(value = "SELECT cm FROM CitaMedica cm, Horario h where cm.horario.id=h.id and cm.estado=1 and h.fecha= CURDATE() and cm.medico.id= :medicoId")
	public List<CitaMedica> getCitasPorMedicoDiarias(@Param("medicoId") int medicoId);

	// Si por error se crean horarios en fines de semana y el cliente agenda una
	// cita, ésta no se mostrará
	@Query(value = "SELECT cm.* FROM Cita_Medica cm JOIN Horario h ON cm.horario_id = h.id WHERE cm.estado = 1 AND h.fecha BETWEEN DATE_SUB(CURDATE(), INTERVAL WEEKDAY(CURDATE()) DAY) AND DATE_ADD(CURDATE(), INTERVAL (4 - WEEKDAY(CURDATE())) DAY) AND cm.medico_id = :medicoId", nativeQuery = true)
	public List<CitaMedica> getCitasPorMedicoSemanales(@Param("medicoId") int medicoId);

	@Query(value = "SELECT cm.* FROM Cita_Medica cm JOIN Horario h ON cm.horario_id = h.id WHERE cm.estado = 1 AND h.fecha BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND LAST_DAY(CURDATE()) AND cm.medico_id = :medicoId", nativeQuery = true)
	public List<CitaMedica> getCitasPorMedicoMensuales(@Param("medicoId") int medicoId);

	@Query(value = "SELECT cm FROM CitaMedica cm, Horario h where cm.horario.id=h.id and cm.paciente.id= :pacienteId")
	public List<CitaMedica> getCitasPorPaciente(@Param("pacienteId") int pacienteId);

	@Query(value = "SELECT count(*) FROM Cita_Medica cm, Horario h where cm.horario_id=h.id and cm.estado=1 and h.fecha= CURDATE() and cm.paciente_id= :pacienteId", nativeQuery = true)
	public int numeroCitasPorDia(@Param("pacienteId") int pacienteId);

}