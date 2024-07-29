package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Consultorios;

public interface IConsultoriosService {

	public void save(Consultorios consultorios);

	public List<Consultorios> findAllConsultorios();
	
	public List<Consultorios> findAllConsultoriosSinEstado();

	public Consultorios findByIdConsultorio(int id);

}
