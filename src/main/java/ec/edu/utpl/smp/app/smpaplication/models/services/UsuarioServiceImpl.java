package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IConsultorioMedicoRepository;
import ec.edu.utpl.smp.app.smpaplication.models.dao.IMedicoEspecialidadRepository;
import ec.edu.utpl.smp.app.smpaplication.models.dao.IPersonaRepository;
import ec.edu.utpl.smp.app.smpaplication.models.dao.IRolesRepository;
import ec.edu.utpl.smp.app.smpaplication.models.dao.IUsuarioRepository;
import ec.edu.utpl.smp.app.smpaplication.models.dao.IUsuarioRolRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.ConsultorioMedico;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Consultorios;
import ec.edu.utpl.smp.app.smpaplication.models.entities.DatosEncapsulados;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Especialidad;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Medico;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoEspecialidad;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Persona;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Roles;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.UsuarioRol;
import ec.edu.utpl.smp.app.smpaplication.models.entities.UsuarioRolId;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IConsultorioMedicoService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IMedicoEspecialidadService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IRolesService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioRolService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService, IRolesService, IUsuarioRolService,
		IConsultorioMedicoService, IMedicoEspecialidadService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private IRolesRepository rolRepository;

	@Autowired
	private IUsuarioRolRepository usuarioRolRepository;

	@Autowired
	private IConsultorioMedicoRepository consultorioMedicoRepository;

	@Autowired
	private IMedicoEspecialidadRepository medicoEspecialidadRepository;

	@Autowired
	private IPersonaRepository personaRepository;

	@Override
	public Iterable<Usuario> getAllUsers() {
		return usuarioRepository.findAll();
	}

	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public void delete(Integer id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public boolean existeUsuario(String username) {
		return usuarioRepository.existeUsuario(username);
	}

	@Override
	public void save(Roles roles) {
		rolRepository.save(roles);

	}

	@Override
	public List<Roles> findAllRoles() {
		return rolRepository.findAll();
	}

	@Override
	public Iterable<UsuarioRol> getAllUsersRol() {
		return usuarioRolRepository.findAll();
	}

	@Override
	public void save(UsuarioRol usuarioRol) {
		usuarioRolRepository.save(usuarioRol);

	}

	@Override
	public UsuarioRol findById(UsuarioRolId id) {
		return usuarioRolRepository.findById(id).orElse(null);
	}

	@Override
	public int findIdRol(int id) {
		return rolRepository.findIdRol(id);
	}

	@Override
	public Usuario findById(Integer id) {
		return usuarioRepository.findById(id).get();
	}

	@Override
	public UsuarioRol findByUsuarioIdAndRolId(int usuarioId, int rolId) {
		return usuarioRolRepository.findByUsuarioIdAndRolId(usuarioId, rolId);
	}

	@Override
	public UsuarioRol findByIdUsuarioRol(int id) {
		return usuarioRolRepository.findByIdUsuarioRol(id);
	}

	@Override
	public void save(ConsultorioMedico consultorioMedico) {
		consultorioMedicoRepository.save(consultorioMedico);

	}

	@Override
	public Iterable<Usuario> findAllMedicos() {
		return usuarioRepository.findAllMedicos();
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

	public ConsultorioMedico findByMedicoId(Integer medicoId) {
		return consultorioMedicoRepository.findByMedicoId(medicoId);
	}

	@Override
	public Roles findById(int id) {
		return rolRepository.findById(id).get();
	}

	@Override
	public ConsultorioMedico findByMedicoIdEdit(int id) {
		return consultorioMedicoRepository.findByMedicoIdEdit(id);
	}

	@Override
	public void save(MedicoEspecialidad medicoEspecialidad) {
		medicoEspecialidadRepository.save(medicoEspecialidad);

	}

	@Override
	public List<MedicoEspecialidad> findByMedicoEspecialidadId(Integer medicoId) {
		return medicoEspecialidadRepository.findByMedicoEspecialidadId(medicoId);
	}

	@Override
	public MedicoEspecialidad findByMedicoEspecialidadIdEdit(int id) {
		return medicoEspecialidadRepository.findByMedicoEspecialidadIdEdit(id);
	}

	@Override
	public int updateConsultorioId(int oldConsultorioId, int newConsultorioId, int medicoId, Date fecha) {
		return consultorioMedicoRepository.updateConsultorioId(oldConsultorioId, newConsultorioId, medicoId, fecha);
	}

	@Override
	public int updateEspecialidadId(int oldEspecialidadId, int newEspecialidadId, int medicoId) {
		return medicoEspecialidadRepository.updateEspecialidadId(oldEspecialidadId, newEspecialidadId, medicoId);
	}

	@Override
	public List<UsuarioRol> findByUsuarioId(int id) {
		return usuarioRolRepository.findByUsuarioId(id);
	}

	@Override
	public List<DatosEncapsulados> findConsultoriosWithMedico() {
		List<Object[]> results = consultorioMedicoRepository.findConsultoriosWithMedico();

		List<DatosEncapsulados> consultorioMedicoDTOs = new ArrayList<>();

		for (Object[] result : results) {
			Integer consultorioId = (Integer) result[0];
			String consultorioNombre = (String) result[1];
			Integer consultorioEstado = (Integer) result[2];
			Integer medicoId = result[3] != null ? (Integer) result[3] : null;
			Integer personaId = result[4] != null ? (Integer) result[4] : null;
			Date fechaAsignacion = (Date) result[5];
			Integer especialidadId = result[6] != null ? (Integer) result[6] : null;
			String especialidadNombre = result[7] != null ? (String) result[7] : null;

			DatosEncapsulados dto = new DatosEncapsulados();
			Consultorios consultorio = new Consultorios();
			consultorio.setId(consultorioId);
			consultorio.setNombre(consultorioNombre);
			consultorio.setEstado(consultorioEstado);

			Medico medico = null;

			if (medicoId != null) {
				medico = new Medico();
				medico.setId(medicoId);

				Persona persona = personaRepository.findByIdP(personaId);
				if (persona.getId() != 0) {
					persona.setId(persona.getId());
					medico.setPersona(persona);
				}
			}

			Especialidad especialidad = null;
			if (especialidadId != null) {
				especialidad = new Especialidad();
				especialidad.setId(especialidadId);
				especialidad.setNombre(especialidadNombre);
			}

			System.out.println("nombres del medico " + personaId);

			dto.setConsultorio(consultorio);
			dto.setMedico(medico);
			dto.setEspecialidad(especialidad);

			ConsultorioMedico consultorioMedico = new ConsultorioMedico();
			consultorioMedico.setConsultorio(consultorio);
			consultorioMedico.setMedico(medico);
			consultorioMedico.setFecha(fechaAsignacion);

			dto.setConsultorioMedico(consultorioMedico);

			consultorioMedicoDTOs.add(dto);
		}

		return consultorioMedicoDTOs;
	}

	@Override
	public void delete(MedicoEspecialidad medicoEspecialidad) {
		medicoEspecialidadRepository.delete(medicoEspecialidad);

	}

	@Override
	public List<MedicoEspecialidad> findByMedicoEspecialidadIdMultiple(int medicoId) {
		return medicoEspecialidadRepository.findByMedicoEspecialidadIdMultiple(medicoId);
	}

	@Override
	public int findByConsultorioId(int id) {
		return consultorioMedicoRepository.findByConsultorioId(id);
	}

	@Override
	public int findByMedicoEspecialidadCount(int id) {
		return medicoEspecialidadRepository.findByMedicoEspecialidadCount(id);
	}

}
