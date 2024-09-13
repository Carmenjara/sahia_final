package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

		// Verificar que el campo rol no sea vacío
		if (result.hasErrors() || usuarioDTO.getUserRol().getRol().getId() == 0) {
			if (usuarioDTO.getUserRol().getRol().getId() == 0) {
				List<Roles> roles = rolesService.findAllRoles(); // Reenviar los roles
				model.addAttribute("roles", roles);
				model.addAttribute("error", "Seleccione un rol válido.");
			}
			return "usuario/crear_usuario";
		}

		// Verificar si el nombre de usuario ya existe
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
			usuario.setEnabled(1);
			usuarioService.save(usuario);

			// Crear y guardar UsuarioRol
			UsuarioRolId usuarioRolId = new UsuarioRolId(usuario.getId(), usuarioRol.getRol().getId());
			usuarioRol.setId(usuarioRolId);
			usuarioRol.setUsuario(usuario);
			usuarioRolService.save(usuarioRol);

			if (usuarioRol.getRol().getId() == 3) { // Si es un médico
				Medico medico = new Medico();
				medico.setPersona(persona);
				medico.setEstado(1);
				medicoService.save(medico);

				// Manejar el Consultorio
				Consultorios consultorio = consultoriosService
						.findByIdConsultorio(consultorioMedico.getConsultorio().getId());
				consultorio.setEstado(0); // Consultorio ocupado
				consultoriosService.save(consultorio);

				ConsultorioMedicoId consultorioMedicoId = new ConsultorioMedicoId(
						consultorioMedico.getConsultorio().getId(), medico.getId());
				consultorioMedico.setId(consultorioMedicoId);
				consultorioMedico.setMedico(medico);
				consultorioMedico.setFecha(new Date());
				consultorioMedicoService.save(consultorioMedico);

				// Guardar las especialidades seleccionadas
				for (Integer especialidadId : usuarioDTO.getEspecialidadesSeleccionadas()) {
					Especialidad especialidad = especialidadService.findByIdEspecialidad(especialidadId);

					MedicoEspecialidad medicoEspecialidad = new MedicoEspecialidad();
					MedicoEspecialidadId medicoEspecialidadId = new MedicoEspecialidadId(medico.getId(),
							especialidadId);
					medicoEspecialidad.setId(medicoEspecialidadId);
					medicoEspecialidad.setMedico(medico);
					medicoEspecialidad.setEspecialidad(especialidad);

					medicoEspecialidadService.save(medicoEspecialidad);

					especialidad.setEstado(0); // Especialidad ocupada
					especialidadService.save(especialidad);
				}

			} else if (usuarioRol.getRol().getId() == 4) { // Si es un paciente
				Paciente pacienteNuevo = new Paciente();
				pacienteNuevo.setPersona(persona);
				pacienteService.save(pacienteNuevo);
			}

			status.setComplete();
			flash.addFlashAttribute("success",
					"El usuario para " + persona.getNombres() + " se registró correctamente.");
			return "redirect:/usuarios/listar_usuarios";
		}
	}

	/* -------------- Editar Usuarios -------------- */

	@RequestMapping("/editar_usuario/{id}")
	public String mostarEditarUsuarioRol(@PathVariable("id") int id, Model model, RedirectAttributes flash) {
		Usuario usuario = usuarioService.findById(id);
		if (usuario == null) {
			flash.addFlashAttribute("error", "Usuario no existe para Editar, por favor revise.");
			return "redirect:/usuarios/listar_usuarios";
		}

		List<Roles> roles = rolesService.findAllRoles();
		List<Consultorios> consultorios = new ArrayList<Consultorios>();
		List<Especialidad> especialidades = new ArrayList<Especialidad>();
		DatosEncapsulados usuarioDTO = new DatosEncapsulados();
		usuarioDTO.setUsuario(usuario);

		UsuarioRol userRol = usuarioRolService.findByIdUsuarioRol(usuario.getId());
		if (userRol == null) {
			userRol = new UsuarioRol();
		}
		usuarioDTO.setUserRol(userRol);
		usuarioDTO.setPersona(usuario.getPersona());
		boolean mostrarConsultorio = false;

		if (userRol.getRol().getId() == 3) {
			mostrarConsultorio = true;
			usuarioDTO.setMedico(medicoService.findByUsuarioId(usuario.getId()));

			usuarioDTO.setConsultorioMedico(consultorioMedicoService.findByMedicoId(usuarioDTO.getMedico().getId()));

			// Se valida que consultorio no este null, por si no tiene asignado
			if (usuarioDTO.getConsultorioMedico() == null) {
				usuarioDTO.setConsultorioMedico(new ConsultorioMedico());
			} else {
				// se muestra el vinculado
				consultorios.add(usuarioDTO.getConsultorioMedico().getConsultorio());

			}

			// Obtener especialidades asociadas al médico
			List<MedicoEspecialidad> especialidadesMedico = medicoEspecialidadService
					.findByMedicoEspecialidadIdMultiple(usuarioDTO.getMedico().getId());
			if (especialidadesMedico == null) {
				especialidadesMedico = new ArrayList<>();
			}
			usuarioDTO.setMedicoEspecialidades(especialidadesMedico);

			// Obtener los IDs de las especialidades vinculadas al médico
			List<Integer> medicoEspecialidadesIds = usuarioDTO.getMedicoEspecialidades().stream()
					.map(medicoEspecialidad -> medicoEspecialidad.getEspecialidad().getId())
					.collect(Collectors.toList());

			// Se envía al objeto general las especialidades
			usuarioDTO.setEspecialidadesSeleccionadas(medicoEspecialidadesIds);
			// Añadir la lista de IDs al modelo
			model.addAttribute("medicoEspecialidadesIds", usuarioDTO.getEspecialidadesSeleccionadas());
		} else if (userRol.getRol().getId() == 4) {
			usuarioDTO.setPaciente(pacienteService.getPacienteByUsuario(usuario.getId()));
		}

		consultorios.addAll(consultoriosService.findAllConsultorios());
		especialidades.addAll(especialidadService.findAllEspecialidadesSinEstado()); // Añadir todas las especialidades
																						// al Set

		model.addAttribute("mostrarConsultorio", mostrarConsultorio);
		model.addAttribute("consultorios", consultorios);
		model.addAttribute("especialidades", especialidades); // Convertir Set a Lista
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
		Usuario usuarioExistente = usuarioService.findById(id);

		if (usuarioExistente == null) {
			// Manejo de error
			return "error";
		}

		UsuarioRol userRol = usuarioDTO.getUserRol();

		if (userRol.getRol().getId() == 3) { // Médico
			Medico medico = medicoService.findByUsuarioId(usuarioExistente.getId());
			// Guarda consultorio
			Consultorios consultorio = consultoriosService
					.findByIdConsultorio(usuarioDTO.getConsultorioMedico().getConsultorio().getId());

			// Se obtiene la relación anterior al cambio de ConsultorioMedico
			ConsultorioMedico consultorioMed = consultorioMedicoService.findByMedicoId(usuarioDTO.getMedico().getId());

			if (consultorioMed != null) {
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
			} else {

				ConsultorioMedico consultorioMedico = new ConsultorioMedico();
				// System.out.println("cuando no hay consultorioMed "+consultorio.getId());
				// Manejar el Consultorio

				consultorio.setEstado(0); // Consultorio ocupado
				consultoriosService.save(consultorio);

				ConsultorioMedicoId consultorioMedicoId = new ConsultorioMedicoId(consultorio.getId(), medico.getId());
				consultorioMedico.setId(consultorioMedicoId);
				consultorioMedico.setMedico(medico);
				consultorioMedico.setConsultorio(consultorio);
				consultorioMedico.setFecha(new Date());
				consultorioMedicoService.save(consultorioMedico);

			}

			// 1. Obtener las especialidades seleccionadas en el formulario
			List<Integer> especialidadesSeleccionadasIds = usuarioDTO.getEspecialidadesSeleccionadas();

			// 2. Obtener las especialidades actualmente asociadas al médico
			List<MedicoEspecialidad> especialidadesActuales = medicoEspecialidadService
					.findByMedicoEspecialidadId(medico.getId());

			if (especialidadesActuales.isEmpty()) {
				System.out.println("especialidades actuales ENTRAAA");
				// 4. Agregar nuevas relaciones
				for (Integer especialidadId : especialidadesSeleccionadasIds) {
					System.out.println("especialidades actuales ENTRAAA foooooooor");
					MedicoEspecialidad nuevaRelacion = new MedicoEspecialidad();
					Especialidad nuevaEspecialidad = especialidadService.findByIdEspecialidad(especialidadId);
					System.out.println("especialidades actuales ENTRAAA foooooooor" + nuevaEspecialidad.getNombre());
					nuevaEspecialidad.setEstado(0);
					especialidadService.save(nuevaEspecialidad);

					// Nueva relacion medico-especialidad
					MedicoEspecialidadId medicoEspecialidadoId = new MedicoEspecialidadId(medico.getId(),
							nuevaEspecialidad.getId());

					nuevaRelacion.setId(medicoEspecialidadoId);
					nuevaRelacion.setMedico(medico);
					nuevaRelacion.setEspecialidad(nuevaEspecialidad);
					medicoEspecialidadService.save(nuevaRelacion);

				}
			} else {

				// 3. Eliminar relaciones no seleccionadas
				for (MedicoEspecialidad me : especialidadesActuales) {
					if (!especialidadesSeleccionadasIds.contains(me.getEspecialidad().getId())) {
						// valida que si especialidad no esta relacionada con medico la ponga en 1 =>
						// Disponible
						// --- validar que si ya no queda ninguna relacion poner en estado 1 y guardar
						// multiple

						Especialidad especialidadActEstado = especialidadService
								.findByIdEspecialidad(me.getEspecialidad().getId());
						if (medicoEspecialidadService
								.findByMedicoEspecialidadCount(me.getEspecialidad().getId()) == 1) {

							especialidadActEstado.setEstado(1);
						}
						especialidadActEstado.setFechaModificacion(new Date());
						especialidadService.save(especialidadActEstado);

						medicoEspecialidadService.delete(me);
					}
				}

				// 4. Agregar nuevas relaciones
				for (Integer especialidadId : especialidadesSeleccionadasIds) {
					boolean yaAsociada = especialidadesActuales.stream()
							.anyMatch(me -> me.getEspecialidad().getId() == (especialidadId));
					if (!yaAsociada) {
						Especialidad nuevaEspecialidad = especialidadService.findByIdEspecialidad(especialidadId);
						MedicoEspecialidad nuevaRelacion = new MedicoEspecialidad();
						nuevaRelacion.setMedico(medico);
						nuevaRelacion.setEspecialidad(nuevaEspecialidad);
						medicoEspecialidadService.save(nuevaRelacion);
					}
				}
			}

			// Actualizar consultorio y demás lógica que ya tienes...
			personaService.save(persona);

		} else if (userRol.getRol().getId() == 4) { // Paciente
			Paciente pacienteNuevo = pacienteService.getPacienteByUsuario(usuarioExistente.getId());
			pacienteNuevo.setPersona(persona);
			pacienteNuevo.setEdad(pacienteNuevo.getEdad());
			pacienteService.save(pacienteNuevo);
			personaService.save(persona);
		} else {
			// Guardar solo datos del usuario
			personaService.save(persona);

			Usuario usuario = usuarioDTO.getUsuario();
			usuarioExistente.setUsername(usuario.getUsername());
			if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
				String hashedPassword = passwordEncoder.encode(usuario.getPassword());
				usuarioExistente.setPassword(hashedPassword);
			}
			usuarioExistente.setEnabled(usuario.getEnabled());
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
