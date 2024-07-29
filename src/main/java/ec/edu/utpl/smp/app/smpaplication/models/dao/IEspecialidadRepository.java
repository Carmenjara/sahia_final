package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Especialidad;

@Repository
public interface IEspecialidadRepository extends JpaRepository<Especialidad, Integer> {
	@Query("SELECT r.id, r.nombre, r.descripcion, r.estado FROM Especialidad r where r.estado=1")
	List<Object[]> findAllEspecialidades();

	@Query("SELECT r.id, r.nombre, r.descripcion, r.estado FROM Especialidad r")
	List<Object[]> findAllEspecialidadesSinEstado();

	@Query("SELECT r FROM Especialidad r WHERE r.id= :id")
	public Especialidad findByIdEspecialidad(int id);

}