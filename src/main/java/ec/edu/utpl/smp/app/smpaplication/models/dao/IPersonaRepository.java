package ec.edu.utpl.smp.app.smpaplication.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Persona;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
	// Obtener persona por cédula
	@Query(value = "SELECT id,identificacion,nombres, apellidos, correo,fechaNacimiento, genero FROM Persona WHERE identificacion=:identificacion", nativeQuery = true)
	public Persona findByCedulapersona(Long identificacion);
	
	@Query(value = "SELECT * FROM Persona WHERE id=:id", nativeQuery = true)
	public Persona findByIdP(int id);

}
