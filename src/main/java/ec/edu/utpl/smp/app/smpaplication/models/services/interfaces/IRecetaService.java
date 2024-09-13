package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Receta;

public interface IRecetaService {

	public void save(Receta receta);

	public Receta get(Integer id);

	public void delete(Integer id);

	public List<Receta> getAllRecetas();

	public List<Receta> getAllRecetaPorPaciente(int idPaciente);
}