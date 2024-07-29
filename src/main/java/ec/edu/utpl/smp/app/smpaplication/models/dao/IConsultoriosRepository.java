package ec.edu.utpl.smp.app.smpaplication.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Consultorios;

@Repository
public interface IConsultoriosRepository extends JpaRepository<Consultorios, Integer> {

	@Query("SELECT r.id, r.nombre, r.descripcion, r.ubicacion, r.estado FROM Consultorios r where r.estado=1")
	List<Object[]> findAllConsultorios();

	@Query("SELECT r.id, r.nombre, r.descripcion, r.ubicacion, r.estado FROM Consultorios r")
	List<Object[]> findAllConsultoriosSinEstado();

	@Query("SELECT r FROM Consultorios r WHERE r.id= :id")
	public Consultorios findByIdConsultorio(int id);
	
	
}
