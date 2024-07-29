package ec.edu.utpl.smp.app.smpaplication.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.utpl.smp.app.smpaplication.models.entities.HistorialMedico;

@Repository
public interface IHistoriaMedicoRepository extends JpaRepository<HistorialMedico, Integer> {

}