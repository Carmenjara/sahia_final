package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Horario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoHorario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoHorarioId;

@Repository
public interface IMedicoHorarioRepository extends JpaRepository<MedicoHorario, MedicoHorarioId> {

	MedicoHorario findByMedicoIdAndHorarioId(int medicoId, int horarioId);

	
	@Query("SELECT mh.horario FROM MedicoHorario mh WHERE mh.medico.id = :medicoId")
	List<Horario> findHorariosByMedicoId(int medicoId);
	
	@Query("SELECT mh.horario FROM MedicoHorario mh WHERE mh.horario.id = :horarioId")
	MedicoHorario findByHorarioId(int horarioId);
	
	List<MedicoHorario> findByHorarioId(Integer horarioId);
	
	void deleteByHorarioId(Integer horarioId);

}