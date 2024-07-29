package ec.edu.utpl.smp.app.smpaplication.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Receta;

@Repository
public interface IRecetaRepository extends JpaRepository<Receta, Integer> {

}