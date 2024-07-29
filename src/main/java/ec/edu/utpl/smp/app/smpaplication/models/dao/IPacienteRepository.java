package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Paciente;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {

	Optional<Paciente> findByPersonaIdentificacion(String identificacion);

	@Query(value = "SELECT pa from Usuario u, Persona p, Paciente pa where u.persona.id=p.id and pa.persona.id=p.id and u.id= :idUsuario")
	public Paciente getPacienteByUsuario(int idUsuario);

}