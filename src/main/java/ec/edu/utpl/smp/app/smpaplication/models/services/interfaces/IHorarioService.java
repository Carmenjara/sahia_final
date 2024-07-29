package ec.edu.utpl.smp.app.smpaplication.models.services.interfaces;

import java.util.Date;
import java.util.List;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Horario;

public interface IHorarioService {

	public Iterable<Horario> getAllHorarios();

	public void save(Horario horario);

	public Horario get(Integer id);
	
	public Horario getPorTipo(String tipo);

	public void delete(Integer id);

	public Horario findById(int horarioId);

	public List<Date> obtenerFechasDisponibles(int estado, int medicoId);

	public List<Horario> obtenerHorariosDisponibles(int medicoId, Date fecha);

	void crearHorariosPorFecha(Date fecha, Integer medicoId);

	public Iterable<Horario> getHorarioPorUsuario(int idUsuario);

}