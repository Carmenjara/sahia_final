package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Persona;

public interface IPersonaService {

	public Iterable<Persona> getAllPersonas();

	public void save(Persona persona);

	public Persona get(Integer id);

	public void delete(Integer id);
	
	public Persona findByIdP(int id);

	/*
	 * Cuando se trabaja con inyección de dependencias, se debe trabajar con
	 * interfaces para que el código pueda ser más mantenible y se pueda adaptar a
	 * cosas nuevas
	 */

}