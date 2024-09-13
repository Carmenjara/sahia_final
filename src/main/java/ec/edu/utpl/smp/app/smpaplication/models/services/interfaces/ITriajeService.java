package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Triaje;

public interface ITriajeService {

	public Iterable<Triaje> getAllTriajes();

	public void save(Triaje triaje);

	public Triaje get(Integer id);

	public void delete(Integer id);

}