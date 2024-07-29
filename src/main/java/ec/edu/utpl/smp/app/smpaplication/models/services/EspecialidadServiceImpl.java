package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IEspecialidadRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Especialidad;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IEspecialidadService;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

	@Autowired
	private IEspecialidadRepository especialidadRepository;

	@Override
	public Especialidad get(Integer id) {
		return especialidadRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		especialidadRepository.deleteById(id);

	}

	@Override
	public Iterable<Especialidad> getAllEspecialidades() {
		return especialidadRepository.findAll();
	}

	@Override
	public void save(Especialidad especialidad) {
		especialidadRepository.save(especialidad);

	}

	@Override
	public List<Especialidad> findAllEspecialidades() {
		List<Object[]> results = especialidadRepository.findAllEspecialidades();
		List<Especialidad> especialidades = new ArrayList<>();
		for (Object[] result : results) {
			Especialidad especialidad = new Especialidad();
			especialidad.setId((Integer) result[0]);
			especialidad.setNombre((String) result[1]);
			especialidad.setDescripcion((String) result[2]);
			especialidad.setEstado((Integer) result[3]);
			especialidades.add(especialidad);
		}
		return especialidades;
	}

	@Override
	public List<Especialidad> findAllEspecialidadesSinEstado() {
		List<Object[]> results = especialidadRepository.findAllEspecialidadesSinEstado();
		List<Especialidad> especialidades = new ArrayList<>();
		for (Object[] result : results) {
			Especialidad especialidad = new Especialidad();
			especialidad.setId((Integer) result[0]);
			especialidad.setNombre((String) result[1]);
			especialidad.setDescripcion((String) result[2]);
			especialidad.setEstado((Integer) result[3]);
			especialidades.add(especialidad);
		}
		return especialidades;
	}

	@Override
	public Especialidad findByIdEspecialidad(int id) {
		return especialidadRepository.findByIdEspecialidad(id);
	}

}