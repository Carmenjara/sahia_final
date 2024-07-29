package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Horario;

@Repository
public interface IHorarioRepository extends JpaRepository<Horario, Integer> {

	public Horario findById(int horarioId);

	@Query(value = "SELECT DISTINCT h.fecha FROM MedicoHorario hm JOIN hm.horario h WHERE h.estado = :estado AND hm.medico.id = :medicoId AND h.fecha >= CURDATE()")
	List<Date> obtenerFechasPorMedico(@Param("estado") int estado, @Param("medicoId") int medicoId);

	//@Query(value = "SELECT h.id, h.hora FROM MedicoHorario hm JOIN Horario h ON hm.horario.id = h.id WHERE h.estado = 1 AND hm.medico.id = :medicoId AND h.hora > CURTIME() and h.fecha = :fecha")
	@Query(value = "SELECT h.id, h.hora FROM MedicoHorario hm JOIN Horario h ON hm.horario.id = h.id WHERE h.estado = 1 AND hm.medico.id = :medicoId and h.fecha = :fecha")
	List<Object[]> obtenerHorariosDisponibles(int medicoId, Date fecha);

	@Query(value = "SELECT h FROM Usuario u JOIN Medico m ON m.persona.id = u.persona.id JOIN MedicoHorario mh ON mh.medico.id = m.id JOIN Horario h ON h.id = mh.horario.id WHERE u.id = :idUsuario")
	public Iterable<Horario> getHorarioPorUsuario(int idUsuario);

	@Query(value = "SELECT h FROM Horario h where h.tipo = :tipo")
	public Horario getPorTipo(String tipo);
}