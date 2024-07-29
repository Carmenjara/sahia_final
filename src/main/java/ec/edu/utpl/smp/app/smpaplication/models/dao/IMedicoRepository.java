package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Medico;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, Integer> {

	@Query("SELECT me.medico FROM MedicoEspecialidad me WHERE me.especialidad.id = :especialidadId")
	List<Medico> findMedicosByEspecialidadId(int especialidadId);
	// List<Medico> findMedicosByEspecialidadId(@Param("especialidadId") int
	// especialidadId);
	
	@Query(value = "SELECT m FROM Usuario u, UsuarioRol ur, Roles r, Medico m where m.persona.id=u.persona.id and r.nombre='MEDICO' and r.id=ur.rol.id and u.id = :usuarioId")
    Medico findByUsuarioId(Integer usuarioId);

}