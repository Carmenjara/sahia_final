package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ec.edu.utpl.smp.app.smpaplication.models.entities.ConsultorioMedico;
import ec.edu.utpl.smp.app.smpaplication.models.entities.ConsultorioMedicoId;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Consultorios;
import ec.edu.utpl.smp.app.smpaplication.models.entities.DatosEncapsulados;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Especialidad;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Medico;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoEspecialidad;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoEspecialidadId;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Paciente;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Persona;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Roles;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.UsuarioRol;
import ec.edu.utpl.smp.app.smpaplication.models.entities.UsuarioRolId;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IConsultorioMedicoService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IConsultoriosService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IEspecialidadService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IMedicoEspecialidadService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IMedicoService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPacienteService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPersonaService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IRolesService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioRolService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
@SessionAttributes("usuarioDTO")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IPersonaService personaService;

	@Autowired
	private IRolesService rolesService;

	@Autowired
	private IConsultoriosService consultoriosService;

	@Autowired
	private IConsultorioMedicoService consultorioMedicoService;

	@Autowired
	private IMedicoService medicoService;

	@Autowired
	private IPacienteService pacienteService;

	@Autowired
	private IUsuarioRolService usuarioRolService;

	@Autowired
	private IEspecialidadService especialidadService;

	@Autowired
	private IMedicoEspecialidadService medicoEspecialidadService;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/* -------------- Gestión Usuarios -------------- */

	/* -------------- Inicio Usuarios -------------- */

	@RequestMapping("/inicio_usuarios")
	public String inicioUser(Model model) {
		model.addAttribute("titulo", "Gestión Usuarios");
		return "usuario/inicio_usuario";
	}

	/* -------------- Crear User-------------- */

	@RequestMapping("/crear_usuario")
	public String crearUsuario(Model model) {
		DatosEncapsulados usuarioDTO = new DatosEncapsulados();
		Persona persona = new Persona();
		usuarioDTO.setPersona(persona);

		List<Roles> roles = rolesService.findAllRoles(); // Obtener la lista de roles
		List<Consultorios> consultorios = consultoriosService.findAllConsultorios(); // Obtener la lista de consultorios
		List<Especialidad> especialidades = especialidadService.findAllEspecialidadesSinEstado(); // Obtener la lista de
																							// especialdiades

		model.addAttribute("roles", roles);
		model.addAttribute("consultorios", consultorios);
		model.addAttribute("especialidades", especialidades);
		/// List<Item> listRangos = itemService.listaItems(id);
		model.addAttribute("titulo", "Registrar Usuario");
		model.addAttribute("usuarioDTO", usuarioDTO);
		// model.addAttribute("items_rango", listRangos);

		return "usuario/crear_usuario";
	}

	/* -------------- Listado Usuarios -------------- */

	@RequestMapping("/listar_usuarios")
	public String listarUsuarios(Model model) {
		/*
		 * List<User> listUser = userService.getAllUsers(); personalService.listAll();
		 */
		model.addAttribute("titulo", "Usuarios Registrados");
		model.addAttribute("listUsuarios", usuarioService.getAllUsers());

		return "usuario/listar_usuario";
	}

	@PostMapping("/guardar_usuario")
	public String guardarUsuario(@ModelAttribute("usuarioDTO") DatosEncapsulados usuarioDTO, BindingResult result,
			SessionStatus status, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
			return "crearUsuario";
		}

		Persona persona = usuarioDTO.getPersona();
		Usuario usuario = usuarioDTO.getUsuario();

		UsuarioRol usuarioRol = usuarioDTO.getUserRol();
		ConsultorioMedico consultorioMedico = usuarioDTO.getConsultorioMedico();
		Consultorios consultorio = consultoriosService.findByIdConsultorio(consultorioMedico.getConsultorio().getId());

		MedicoEspecialidad medicoEspecialidad = usuarioDTO.getMedicoEspecialidad();
		Especialidad especialidad = especialidadService
				.findByIdEspecialidad(medicoEspecialidad.getEspecialidad().getId());

		// Verificar que el campo rol no sea vacío
		if (result.hasErrors() || usuarioDTO.getUserRol().getRol().getId() == 0) {
			if (usuarioDTO.getUserRol().getRol().getId() == 0) {
				List<Roles> roles = rolesService.findAllRoles(); // Reenviar los roles
				model.addAttribute("roles", roles);
				model.addAttribute("error", "Seleccione un rol válido.");
			}
			return "usuario/crear_usuario";
		}

		else

		// si usuario existe se redirecciona al mismo formulario con la data
		// preingresada
		if (usuarioService.existeUsuario(usuario.getUsername())) {

			model.addAttribute("error", "El nombre de usuario ya está en uso. Pruebe con otro.");
			model.addAttribute("usuarioDTO", usuarioDTO);

			List<Roles> roles = rolesService.findAllRoles(); // Reenviar los roles
			model.addAttribute("roles", roles);
			flash.addFlashAttribute("error", "El nombre de usuario ya está en uso.");
			return "usuario/crear_usuario"; // Mostrar mensaje de error en la misma página
		} else {

			// Guardar Persona
			personaService.save(persona);

			// Asignar Persona al Usuario y guardar Usuario
			usuario.setPersona(persona);

			// Encriptar clave con Bcrypt
			String hashedPassword = passwordEncoder.encode(usuario.getPassword());
			usuario.setPassword(hashedPassword);
			// Establecer usuario como activo
			usuario.setEnabled(1);
			usuarioService.save(usuario);

			// Crear y guardar UsuarioRol
			UsuarioRolId usuarioRolId = new UsuarioRolId(usuario.getId(), usuarioRol.getRol().getId());
			usuarioRol.setId(usuarioRolId);
			usuarioRol.setUsuario(usuario);
			usuarioRolService.save(usuarioRol);

			if (usuarioRol.getRol().getId() == 3) {
				Medico medico = new Medico();
				medico.setPersona(persona);
				medicoService.save(medico);

				System.out.println(medico.getId() + " medico.getId()");
				System.out.println(consultorioMedico.getConsultorio() + " consultorioMedico");

				System.out.println("El consultorio a Modificar es: " + consultorio.getId());

				consultorio.setEstado(0); // Se Establece consultorio como ocupado 0

				// Crear y guardar UsuarioRol
				ConsultorioMedicoId consultorioMedicoId = new ConsultorioMedicoId(
						consultorioMedico.getConsultorio().getId(), medico.getId());
				consultorioMedico.setId(consultorioMedicoId);
				consultorioMedico.setMedico(medico);
				// Establecer fecha del sistema

				consultorioMedico.setFecha(new Date());
				consultorioMedicoService.save(consultorioMedico);
				consultoriosService.save(consultorio);

				especialidad.setEstado(0); // Se Establece consultorio como ocupado 0

				// Crear y guardar UsuarioRol
				MedicoEspecialidadId medicoEspecialidadId = new MedicoEspecialidadId(medico.getId(),
						medicoEspecialidad.getEspecialidad().getId());
				medicoEspecialidad.setId(medicoEspecialidadId);
				medicoEspecialidad.setMedico(medico);
				// Establecer fecha del sistema

				// medicoEspecialidad.setFecha(new Date());
				medicoEspecialidadService.save(medicoEspecialidad);
				especialidadService.save(especialidad);

				// se ingresa un Paciente
			} else if (usuarioRol.getRol().getId() == 4) {
				Paciente pacienteNuevo = new Paciente();
				pacienteNuevo.setPersona(persona);
				pacienteService.save(pacienteNuevo);

			}
			// System.out.println(consultorioMedico);

			status.setComplete();
			flash.addFlashAttribute("success",
					"El usuario para " + persona.getNombres() + " se registró correctamente.");
			System.out.println("Datos Guardados");
			return "redirect:/usuarios/listar_usuarios";
		}
	}

	/* -------------- Editar Usuarios -------------- */

	@RequestMapping("/editar_usuario/{id}")
	public String mostarEditarUsuarioRol(@PathVariable("id") int id, Model model, RedirectAttributes flash) {
		Usuario usuario = usuarioService.findById(id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "Usuario no exite para Editar, por favor revise.");
			return "redirect:/usuarios/listar_usuarios";
		}

		List<Roles> roles = rolesService.findAllRoles(); // Asegúrate de tener este método en tu servicio
		List<Consultorios> consultorios = new ArrayList<Consultorios>();
		List<Especialidad> especialidades = new ArrayList<Especialidad>();
		DatosEncapsulados usuarioDTO = new DatosEncapsulados();
		usuarioDTO.setUsuario(usuario);

		// Obtener y setear el UsuarioRol asociado al Usuario
		UsuarioRol userRol = usuarioRolService.findByIdUsuarioRol(usuario.getId()); // Si hay solo un rol
																					// por usuario
		if (userRol == null) {
			userRol = new UsuarioRol(); // O inicializarlo de alguna manera adecuada
		}
		usuarioDTO.setUserRol(userRol);
		usuarioDTO.setPersona(usuario.getPersona());
		boolean mostrarConsultorio = false;

		if (userRol.getRol().getId() == 3) {
			mostrarConsultorio = true; // Cambia esta lógica según tu necesidad
			usuarioDTO.setMedico(medicoService.findByUsuarioId(usuario.getId()));
			usuarioDTO.setConsultorioMedico(consultorioMedicoService.findByMedicoId(usuarioDTO.getMedico().getId()));
			usuarioDTO.setMedicoEspecialidad(
					medicoEspecialidadService.findByMedicoEspecialidadId(usuarioDTO.getMedico().getId()));

			consultorios.add(usuarioDTO.getConsultorioMedico().getConsultorio());
			especialidades.add(usuarioDTO.getMedicoEspecialidad().getEspecialidad());

		} else if (userRol.getRol().getId() == 4) {
			usuarioDTO.setPaciente(pacienteService.getPacienteByUsuario(usuario.getId()));
		}

		consultorios.addAll(consultoriosService.findAllConsultorios()); // Obtener la lista de consultorios
		especialidades.addAll(especialidadService.findAllEspecialidadesSinEstado()); // Obtener la lista de especialdiades

		model.addAttribute("mostrarConsultorio", mostrarConsultorio);
		model.addAttribute("consultorios", consultorios);
		model.addAttribute("especialidades", especialidades);
		model.addAttribute("usuarioDTO", usuarioDTO);
		model.addAttribute("roles", roles);

		return "usuario/editar_usuario";
	}

	/* -------------- Guardar Usuario - Rol -------------- */

	@PostMapping("/editar_usuario/{id}")
	public String guardarEdidarUsuarioRol(@PathVariable("id") int id,
			@ModelAttribute("usuarioDTO") DatosEncapsulados usuarioDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {
		// Actualizar persona
		Persona persona = usuarioDTO.getPersona();
		// Persona persona2 = personaService
		Paciente pacienteNuevo = usuarioDTO.getPaciente();

		// Encontrar el Usuario existente
		Usuario usuarioExistente = usuarioService.findById(id);

		if (usuarioExistente == null) {
			// Manejo de error
			return "error";
		}

		UsuarioRol userRol = usuarioDTO.getUserRol();

		if (userRol.getRol().getId() == 3) {
			// Guarda consultorio
			Consultorios consultorio = consultoriosService
					.findByIdConsultorio(usuarioDTO.getConsultorioMedico().getConsultorio().getId());

			// Se obtiene la relación anterior al cambio de ConsultorioMedico
			ConsultorioMedico consultorioMed = consultorioMedicoService.findByMedicoId(usuarioDTO.getMedico().getId());

			Consultorios consultorioAnt = consultoriosService
					.findByIdConsultorio(consultorioMed.getConsultorio().getId());
			// Se estable Consultorio como libre antes de vincular a un siguiente
			consultorioAnt.setEstado(1);
			consultoriosService.save(consultorioAnt);

			// Se establece Consultorio como ocupado 0, para el nuevo que se selecciona
			consultorio.setEstado(0);
			consultoriosService.save(consultorio);

			consultorioMedicoService.updateConsultorioId(consultorioAnt.getId(), consultorio.getId(),
					usuarioDTO.getMedico().getId(), new Date());

			// Guarda especialidad

			Especialidad especialidad = especialidadService
					.findByIdEspecialidad(usuarioDTO.getMedicoEspecialidad().getEspecialidad().getId());

			// Se obtiene la relación anterior al cambio de ConsultorioMedico
			MedicoEspecialidad medicoEspecialidad = medicoEspecialidadService
					.findByMedicoEspecialidadId(usuarioDTO.getMedico().getId());

			Especialidad especialidadAnt = especialidadService
					.findByIdEspecialidad(medicoEspecialidad.getEspecialidad().getId());
			// Se estable Consultorio como libre antes de vincular a un siguiente
			especialidadAnt.setEstado(1);
			especialidadService.save(especialidadAnt);

			// Se establece Consultorio como ocupado 0, para el nuevo que se selecciona
			especialidad.setEstado(0);
			especialidadService.save(especialidad);

			medicoEspecialidadService.updateEspecialidadId(especialidadAnt.getId(), especialidad.getId(),
					usuarioDTO.getMedico().getId());

			personaService.save(persona);
		} else if (userRol.getRol().getId() == 4) {

			pacienteNuevo = pacienteService.getPacienteByUsuario(usuarioExistente.getId());
			pacienteNuevo.setPersona(persona);
			pacienteNuevo.setEdad(pacienteNuevo.getEdad());
			pacienteService.save(pacienteNuevo);
			personaService.save(persona);
		} else {
			personaService.save(persona);

			// Actualizar datos del Usuario
			Usuario usuario = usuarioDTO.getUsuario();
			usuarioExistente.setUsername(usuario.getUsername());
			// verifica si se puso una nueva clave
			if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
				// System.out.println("Entro porque si puso clave");
				String hashedPassword = passwordEncoder.encode(usuario.getPassword());
				usuario.setPassword(hashedPassword);
				usuarioExistente.setPassword(usuario.getPassword());
			}
			usuarioExistente.setEnabled(usuario.getEnabled());
			// Guardar cambios del Usuario
			usuarioService.save(usuarioExistente);
		}

		redirectAttrs.addFlashAttribute("success",
				"La información para " + persona.getNombres() + " se modificó correctamente.");
		return "redirect:/usuarios/listar_usuarios";
	}

	/* -------------- Inhabilitar Usuario -------------- */
	@RequestMapping("/inhabilitar_usuario/{id}")
	public String inhabilitarEspecialidad(@PathVariable(name = "id") int id, RedirectAttributes flash,
			SessionStatus status) {
		Usuario usuario = usuarioService.findById(id);
		// Se obtiene el usuario que se encuentra logueado
		Usuario usuarioLogueado = new Usuario();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				usuarioLogueado = usuarioService.findByUsername(userDetails.getUsername());
			}
		}

		// Se inhabilitan Usuario cuando ya no queremos que accedan al sistema
		if (usuarioLogueado.getId() == usuario.getId()) {
			flash.addFlashAttribute("error", "Usted no se puede inhabilitar, se encuentra logueado.");
			return "redirect:/usuarios/listar_usuarios";
		} else if (usuario.getEnabled() == 0) {
			flash.addFlashAttribute("error", "Usuario ya se encuentra inhabilitado.");
			return "redirect:/usuarios/listar_usuarios";
		} else {
			// Establecer estado en 0 como Inhabilitado
			usuario.setEnabled(0);
			usuarioService.save(usuario);
		}

		status.setComplete();
		flash.addFlashAttribute("success", "Se inhabilitó el Usuario de forma exitosa.");
		System.out.println("Datos Guardados");
		return "redirect:/usuarios/listar_usuarios";
	}

}
