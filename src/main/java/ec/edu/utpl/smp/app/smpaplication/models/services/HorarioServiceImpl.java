package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IHorarioRepository;
import ec.edu.utpl.smp.app.smpaplication.models.dao.IMedicoHorarioRepository;
import ec.edu.utpl.smp.app.smpaplication.models.dao.IMedicoRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Horario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Medico;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoHorario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoHorarioId;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IHorarioService;

@Service
public class HorarioServiceImpl implements IHorarioService {

	@Autowired
	private IHorarioRepository horarioRepository;

	@Autowired
	private IMedicoRepository medicoRepository;

	@Autowired
	private IMedicoHorarioRepository medicoHorarioRepository;

	@Override
	public Horario get(Integer id) {
		return horarioRepository.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		medicoHorarioRepository.deleteByHorarioId(id);
		horarioRepository.deleteById(id);

	}

	@Override
	public Iterable<Horario> getAllHorarios() {
		return horarioRepository.findAll();
	}

	@Override
	public void save(Horario horario) {
		horarioRepository.save(horario);

	}

	@Override
	public Horario findById(int horarioId) {
		return horarioRepository.findById(horarioId);
	}

	@Override
	public List<Date> obtenerFechasDisponibles(int estado, int medicoId) {
		return horarioRepository.obtenerFechasPorMedico(estado, medicoId);
	}

	@Override
	public List<Horario> obtenerHorariosDisponibles(int medicoId, Date fecha) {
		// return horarioRepository.obtenerHorariosDisponibles(medicoId, fecha);
		List<Object[]> results = horarioRepository.obtenerHorariosDisponibles(medicoId, fecha);
		List<Horario> horarios = new ArrayList<>();
		for (Object[] result : results) {
			Horario horario = new Horario();
			horario.setId((Integer) result[0]);
			horario.setHora((Time) result[1]);
			horarios.add(horario);
		}
		return horarios;
	}

	@Transactional
	@Override
	public void crearHorariosPorFecha(Date fecha, Integer usuarioId) {
		Medico medico = medicoRepository.findByUsuarioId(usuarioId);
		if (medico == null) {
			throw new IllegalArgumentException("Medico no encontrado para el usuarioId: " + usuarioId);
		}
		Integer medicoId = medico.getId();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);

		// Crear horarios matutinos
		int[][] matutinoHorarios = { { 9, 0 }, { 9, 30 }, { 10, 0 }, { 10, 30 }, { 11, 0 }, { 11, 30 } };
		for (int[] tiempo : matutinoHorarios) {
			calendar.set(Calendar.HOUR_OF_DAY, tiempo[0]);
			calendar.set(Calendar.MINUTE, tiempo[1]);
			Horario horario = new Horario();
			horario.setFecha(new java.sql.Date(fecha.getTime()));
			horario.setHora(new Time(calendar.getTimeInMillis()));
			horario.setTipo("MATUTINO");
			horario.setEstado(1);
			Horario savedHorario = horarioRepository.save(horario);
			MedicoHorarioId medicoHorarioId = new MedicoHorarioId(medicoId, savedHorario.getId());
			MedicoHorario medicoHorario = new MedicoHorario(medicoHorarioId, medico, savedHorario);
			medicoHorarioRepository.save(medicoHorario);
		}

		// Crear horarios vespertinos
		int[][] vespertinoHorarios = { { 16, 0 }, { 16, 30 }, { 17, 0 }, { 17, 30 } };
		for (int[] tiempo : vespertinoHorarios) {
			calendar.set(Calendar.HOUR_OF_DAY, tiempo[0]);
			calendar.set(Calendar.MINUTE, tiempo[1]);
			Horario horario = new Horario();
			horario.setFecha(new java.sql.Date(fecha.getTime()));
			horario.setHora(new Time(calendar.getTimeInMillis()));
			horario.setTipo("VESPERTINO");
			horario.setEstado(1);
			Horario savedHorario = horarioRepository.save(horario);
			MedicoHorarioId medicoHorarioId = new MedicoHorarioId(medicoId, savedHorario.getId());
			MedicoHorario medicoHorario = new MedicoHorario(medicoHorarioId, medico, savedHorario);
			medicoHorarioRepository.save(medicoHorario);
		}
	}

	@Override
	public Iterable<Horario> getHorarioPorUsuario(int idUsuario) {
		return horarioRepository.getHorarioPorUsuario(idUsuario);
	}

	@Override
	public Horario getPorTipo(String tipo) {
		return horarioRepository.getPorTipo(tipo);
	}

}