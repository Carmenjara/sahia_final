package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoEspecialidad;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoEspecialidadId;

public interface IMedicoEspecialidadRepository extends JpaRepository<MedicoEspecialidad, MedicoEspecialidadId> {

	@Query("SELECT r FROM MedicoEspecialidad r WHERE r.id.medicoId = ?1")
	public List<MedicoEspecialidad> findByMedicoEspecialidadId(int id);

	@Query("SELECT r FROM MedicoEspecialidad r WHERE r.id.medicoId = ?1")
	public List<MedicoEspecialidad> findByMedicoEspecialidadIdMultiple(int medicoId);

	@Query("SELECT r FROM MedicoEspecialidad r, Especialidad c WHERE r.medico.id=c.id and c.estado= 0 and r.medico.id = ?1")
	public MedicoEspecialidad findByMedicoEspecialidadIdEdit(int id);

	@Query("SELECT count(*) FROM MedicoEspecialidad r WHERE r.especialidad.id = ?1")
	public int findByMedicoEspecialidadCount(int id);

	@Modifying
	@Transactional
	@Query("UPDATE MedicoEspecialidad cm SET cm.especialidad.id = :newEspecialidadId WHERE cm.especialidad.id = :oldEspecialidadId AND cm.medico.id = :medicoId")
	public int updateEspecialidadId(int oldEspecialidadId, int newEspecialidadId, int medicoId);

}
