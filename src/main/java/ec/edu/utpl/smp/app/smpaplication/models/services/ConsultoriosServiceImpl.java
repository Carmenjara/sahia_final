package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IConsultoriosRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Consultorios;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IConsultoriosService;

@Service
public class ConsultoriosServiceImpl implements IConsultoriosService {

	@Autowired
	IConsultoriosRepository consultoriosRepository;

	@Override
	public void save(Consultorios consultorios) {
		consultoriosRepository.save(consultorios);

	}

	@Override
	public Consultorios findByIdConsultorio(int id) {
		return consultoriosRepository.findByIdConsultorio(id);
	}

	@Override
	public List<Consultorios> findAllConsultorios() {

		List<Object[]> results = consultoriosRepository.findAllConsultorios();
		List<Consultorios> consultorios = new ArrayList<>();
		for (Object[] result : results) {
			Consultorios consultorio = new Consultorios();
			consultorio.setId((Integer) result[0]);
			consultorio.setNombre((String) result[1]);
			consultorio.setDescripcion((String) result[2]);
			consultorio.setUbicacion((String) result[3]);
			consultorio.setEstado((Integer) result[4]);
			consultorios.add(consultorio);
		}
		return consultorios;
	}

	@Override
	public List<Consultorios> findAllConsultoriosSinEstado() {

		List<Object[]> results = consultoriosRepository.findAllConsultoriosSinEstado();
		List<Consultorios> consultorios = new ArrayList<>();
		for (Object[] result : results) {
			Consultorios consultorio = new Consultorios();
			consultorio.setId((Integer) result[0]);
			consultorio.setNombre((String) result[1]);
			consultorio.setDescripcion((String) result[2]);
			consultorio.setUbicacion((String) result[3]);
			consultorio.setEstado((Integer) result[4]);
			consultorios.add(consultorio);
		}
		return consultorios;
	}

}
