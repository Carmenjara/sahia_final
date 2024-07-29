package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Especialidad;

public interface IEspecialidadService {

	public Iterable<Especialidad> getAllEspecialidades();

	public void save(Especialidad especialidad);

	public Especialidad get(Integer id);

	public void delete(Integer id);

	public List<Especialidad> findAllEspecialidades();
	
	public List<Especialidad> findAllEspecialidadesSinEstado();


	public Especialidad findByIdEspecialidad(int id);


}