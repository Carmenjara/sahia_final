package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.Date;

import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoEspecialidad;

public interface IMedicoEspecialidadService {

	public void save(MedicoEspecialidad medicoEspecialidad);
	
	public MedicoEspecialidad findByMedicoEspecialidadId(Integer medicoId);
	
	public MedicoEspecialidad findByMedicoEspecialidadIdEdit(int id);
	
	public int updateEspecialidadId(int oldEspecialidadId, int newEspecialidadId, int medicoId);

}