package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoEspecialidad;

public interface IMedicoEspecialidadService {

	public void save(MedicoEspecialidad medicoEspecialidad);

	public List<MedicoEspecialidad> findByMedicoEspecialidadId(Integer medicoId);

	public MedicoEspecialidad findByMedicoEspecialidadIdEdit(int id);

	public int updateEspecialidadId(int oldEspecialidadId, int newEspecialidadId, int medicoId);

	void delete(MedicoEspecialidad medicoEspecialidad);

	public List<MedicoEspecialidad> findByMedicoEspecialidadIdMultiple(int medicoId);

	public int findByMedicoEspecialidadCount(int id);

}