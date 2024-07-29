package ec.edu.utpl.smp.app.smpaplication.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.utpl.smp.app.smpaplication.models.dao.IConsultorioMedicoRepository;
import ec.edu.utpl.smp.app.smpaplication.models.dao.IMedicoEspecialidadRepository;
import ec.edu.utpl.smp.app.smpaplication.models.dao.IRolesRepository;
import ec.edu.utpl.smp.app.smpaplication.models.dao.IUsuarioRepository;
import ec.edu.utpl.smp.app.smpaplication.models.dao.IUsuarioRolRepository;
import ec.edu.utpl.smp.app.smpaplication.models.entities.ConsultorioMedico;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoEspecialidad;
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
	public MedicoEspecialidad findByMedicoEspecialidadId(Integer medicoId) {
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

}
