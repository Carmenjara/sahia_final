
package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.utpl.smp.app.smpaplication.models.entities.ConsultorioMedico;
import ec.edu.utpl.smp.app.smpaplication.models.entities.ConsultorioMedicoId;

public interface IConsultorioMedicoRepository extends JpaRepository<ConsultorioMedico, ConsultorioMedicoId> {

	@Query("SELECT r FROM ConsultorioMedico r WHERE r.id.medicoid = ?1")
	public ConsultorioMedico findByMedicoId(int id);
	
	@Query("SELECT count(*) FROM ConsultorioMedico r WHERE r.id.consultorioid= ?1")
	public int findByConsultorioId(int id);

	@Query("SELECT r FROM ConsultorioMedico r, Consultorios c WHERE r.consultorio.id=c.id and c.estado= 0 and r.medico.id = ?1")
	public ConsultorioMedico findByMedicoIdEdit(int id);

	@Modifying
	@Transactional
	@Query("UPDATE ConsultorioMedico cm SET cm.consultorio.id = :newConsultorioId, cm.fecha = :fecha  WHERE cm.consultorio.id = :oldConsultorioId AND cm.medico.id = :medicoId")
	public int updateConsultorioId(int oldConsultorioId, int newConsultorioId, int medicoId, Date fecha);


	@Query(value = "SELECT c.id AS consultorio_id, c.nombre AS consultorio_nombre, c.estado AS consultorio_estado, m.id AS medico_id, m.persona_id AS medico_persona, cm.fecha, e.id AS especialidad_id, e.nombre AS especialidad_nombre FROM Consultorio c LEFT JOIN Consultorio_Medico cm ON c.id = cm.consultorio_id LEFT JOIN Medico m ON cm.medico_id = m.id LEFT JOIN Medico_Especialidad me ON m.id = me.medico_id LEFT JOIN Especialidad e ON me.especialidad_id = e.id", nativeQuery = true)
	List<Object[]> findConsultoriosWithMedico();

}
