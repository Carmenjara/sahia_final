
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
	
	@Query("SELECT r FROM ConsultorioMedico r, Consultorios c WHERE r.consultorio.id=c.id and c.estado= 0 and r.medico.id = ?1")
	public ConsultorioMedico findByMedicoIdEdit(int id);
	
	@Modifying
    @Transactional
    @Query("UPDATE ConsultorioMedico cm SET cm.consultorio.id = :newConsultorioId, cm.fecha = :fecha  WHERE cm.consultorio.id = :oldConsultorioId AND cm.medico.id = :medicoId")
    public int updateConsultorioId(int oldConsultorioId, int newConsultorioId, int medicoId, Date fecha);


	@Query("SELECT c, m, cm.fecha FROM Consultorios c LEFT JOIN ConsultorioMedico cm ON c.id = cm.consultorio.id LEFT JOIN Medico m ON cm.medico.id = m.id")
	List<Object[]> findConsultoriosWithMedico();

}
