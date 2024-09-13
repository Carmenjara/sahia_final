package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.utpl.smp.app.smpaplication.models.entities.CitaMedica;
import ec.edu.utpl.smp.app.smpaplication.models.entities.CodeGenerator;
import ec.edu.utpl.smp.app.smpaplication.models.entities.DatosEncapsulados;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Especialidad;
import ec.edu.utpl.smp.app.smpaplication.models.entities.HistorialMedico;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Horario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Medicamento;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Medico;
import ec.edu.utpl.smp.app.smpaplication.models.entities.MedicoHorario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Paciente;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Receta;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.ICitaMedicaService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IEspecialidadService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IHistoriaMedicoService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IHorarioService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IMedicamentoService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IMedicoService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPacienteService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IRecetaService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioService;

@Controller
@RequestMapping("/citamedica")
@SessionAttributes("citamedica")
public class CitaMedicaController {

	@Autowired
	private ICitaMedicaService citaMedicaService;

	@Autowired
	private IEspecialidadService especialidadService;

	@Autowired
	private IMedicoService medicoService;

	@Autowired
	private IPacienteService pacienteService;

	@Autowired
	private IHorarioService horarioService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IMedicamentoService medicamentoService;

	@Autowired
	private IRecetaService recetaService;

	@Autowired
	private IHistoriaMedicoService historiaMedicoService;
	
	@Autowired
	private IHistoriaMedicoService historialMedicoService;

	/* -------------- Gestión Cita Medica -------------- */

	/* -------------- Inicio Gestión Cita Medica -------------- */

	@RequestMapping("/inicio_citamedica")
	public String verPaginaC(Model model) {
		model.addAttribute("titulo", "Gestión de Citas Médicas");
		return "citamedica/inicio_citamedica";
	}

	/* -------------- Crear Citas Médicas General-------------- */

	@RequestMapping("/crear_citamedica")
	public String mostrarPaginaNuevaCita(Map<String, Object> model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		Usuario usuario = usuarioService.findByUsername(username);

		// Se obtiene id de paciente que se encuentra logueado
		Paciente paciente = pacienteService.getPacienteByUsuario(usuario.getId());

		// Datos para cita
		DatosEncapsulados citaDTO = new DatosEncapsulados();

		Horario horario = new Horario();
		MedicoHorario medicoHorario = new MedicoHorario();
		CitaMedica citamedica = new CitaMedica();
		citaDTO.setCita(citamedica);
		// Date fecha = convertStringToDate(fechaString);
		citaDTO.setHorario(horario);

		citaDTO.setPaciente(paciente);

		citaDTO.setMedicoHorario(medicoHorario);

		model.put("citaDTO", citaDTO);
		model.put("titulo", "Registrar Cita Médica");
		model.put("especialidades", especialidadService.findAllEspecialidades());
		model.put("medicos", medicoService.getAllMedicos());

		return "citamedica/crear_citamedica";
	}

	/* -------------- Crear Citas Médicas General-------------- */
	@RequestMapping("/crear_citamedica_paciente")
	public String mostrarPaginaNuevaCitaPaciente(Map<String, Object> model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		Usuario usuario = usuarioService.findByUsername(username);

		// Se obtiene id de paciente que se encuentra logueado
		Paciente paciente = pacienteService.getPacienteByUsuario(usuario.getId());

		// Datos para cita
		DatosEncapsulados citaDTO = new DatosEncapsulados();

		Horario horario = new Horario();
		MedicoHorario medicoHorario = new MedicoHorario();
		CitaMedica citamedica = new CitaMedica();
		citaDTO.setCita(citamedica);
		// Date fecha = convertStringToDate(fechaString);
		citaDTO.setHorario(horario);

		citaDTO.setPaciente(paciente);

		citaDTO.setMedicoHorario(medicoHorario);

		model.put("citaDTO", citaDTO);
		model.put("titulo", "Registrar Cita Médica");
		model.put("especialidades", especialidadService.findAllEspecialidades());
		model.put("medicos", medicoService.getAllMedicos());

		return "citamedica/crear_citamedica_paciente";
	}

	@RequestMapping(value = "/buscar_paciente", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> buscarPaciente(@RequestParam("cedula") String cedula) {
		Map<String, Object> response = new HashMap<>();
		Optional<Paciente> paciente = pacienteService.findByPersonaIdentificacion(cedula);
		if (paciente.isPresent()) {
			response.put("paciente", paciente.get());
		} else {
			response.put("error", "Paciente no encontrado, ingréselo primero!");
		}
		return response;
	}

	@RequestMapping("/medicosPorEspecialidad")
	@ResponseBody
	public List<Medico> getMedicosPorEspecialidad(@RequestParam("especialidadId") int especialidadId) {
		return medicoService.findMedicosByEspecialidadId(especialidadId);
	}

	@RequestMapping("/fechasPorMedico")
	@ResponseBody
	public List<Date> obtenerFechasMedico(@RequestParam("estado") int estado, @RequestParam("medicoId") int medicoId) {
		return horarioService.obtenerFechasDisponibles(estado, medicoId);
	}

	@RequestMapping("/horariosDisponibles")
	@ResponseBody
	public List<Horario> obtenerHorariosDisponibles(@RequestParam("medicoId") int medicoId,
			@RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
		return horarioService.obtenerHorariosDisponibles(medicoId, fecha);
	}

	/* -------------- Guardar Cita Médica General-------------- */

	@RequestMapping(value = "/crear_citamedica", method = RequestMethod.POST)
	public String guardarCitaMedica(@ModelAttribute("citaDTO") DatosEncapsulados citaDTO, RedirectAttributes flash,
			SessionStatus status) {

		CitaMedica citamedica = citaDTO.getCita();
		Horario horario = citaDTO.getHorario();
		Paciente paciente = citaDTO.getPaciente();
		// Obtener el numero de citas al día del paciente
		int citasAlDia = citaMedicaService.numeroCitasPorDia(paciente.getId());

		String mensajeFlash = (citamedica.getId() != 0) ? "Cita Médica Editada Exitosamente!"
				: "Cita Médica Registrada Exitosamente!";

		// Obtener especialidad para código único cita médica
		Especialidad esp = especialidadService.get(citamedica.getEspecialidad().getId());

		// Función para obtener los primeros 4 caracteres de especialidad
		String prefijo = Especialidad.obtenerPrefijoEsp(esp.getNombre());

		// Crear código único
		CodeGenerator codPref = new CodeGenerator();
		citamedica.setNumero(codPref.generarCodigo(prefijo));

		// Establecer estado de cita en 1 - Sin atender
		citamedica.setEstado(1);
		Paciente pp = pacienteService.get(paciente.getId());
		citamedica.setPaciente(pp);

		// Buscar el horario existente usando su ID
		Horario horarioExistente = horarioService.findById(horario.getId());

		int contar = citasAlDia + 1;
		// Solo puede agendar dos citas al día
		if (contar <= 2) {
			if (horarioExistente != null) {
				// Establecer estado en 0 como Utilizado
				System.out.println("Se debe guardar en estado 0 " + horarioExistente);
				// Asigamos id de Horario a Cita Médica
				citamedica.setHorario(horarioExistente);

				horarioExistente.setEstado(0);
				horarioService.save(horarioExistente); // Solo el campo estado se actualiza
			} else {
				System.out.println(horarioExistente);
				flash.addFlashAttribute("error", "Horario no encontrado.");
				return "redirect:/citamedica/listar_citamedica";
			}

			citaMedicaService.save(citamedica);

			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			System.out.println("Datos Guardados");
			return "redirect:/citamedica/listar_citamedica";
		} else {
			flash.addFlashAttribute("error", "El paciente ya no puede agendar citas hoy.");
			return "redirect:/citamedica/listar_citamedica";
		}

	}

	/* -------------- Guardar Cita Médica Paciente-------------- */

	@RequestMapping(value = "/crear_citamedica_paciente", method = RequestMethod.POST)
	public String guardarCitaMedicaPaciente(@ModelAttribute("citaDTO") DatosEncapsulados citaDTO,
			RedirectAttributes flash, SessionStatus status) {

		CitaMedica citamedica = citaDTO.getCita();
		Horario horario = citaDTO.getHorario();
		Paciente paciente = citaDTO.getPaciente();

		// Obtener el numero de citas al día del paciente
		int citasAlDia = citaMedicaService.numeroCitasPorDia(paciente.getId());

		String mensajeFlash = (citamedica.getId() != 0) ? "Cita Médica Editada Exitosamente!"
				: "Cita Médica Registrada Exitosamente!";

		// Obtener especialidad para código único cita médica
		Especialidad esp = especialidadService.get(citamedica.getEspecialidad().getId());

		// Función para obtener los primeros 4 caracteres de especialidad
		String prefijo = Especialidad.obtenerPrefijoEsp(esp.getNombre());

		// Crear código único
		CodeGenerator codPref = new CodeGenerator();
		citamedica.setNumero(codPref.generarCodigo(prefijo));

		// Establecer estado de cita en 1 - Sin atender
		citamedica.setEstado(1);
		Paciente pp = pacienteService.get(paciente.getId());
		citamedica.setPaciente(pp);

		// Buscar el horario existente usando su ID
		Horario horarioExistente = horarioService.findById(horario.getId());
		int contar = citasAlDia + 1;
		// Solo puede agendar dos citas al día
		if (contar <= 2) {
			if (horarioExistente != null) {
				// Establecer estado en 0 como Utilizado
				System.out.println("Se debe guardar en estado 0 " + horarioExistente);
				// Asigamos id de Horario a Cita Médica
				citamedica.setHorario(horarioExistente);

				horarioExistente.setEstado(0);
				horarioService.save(horarioExistente); // Solo el campo estado se actualiza
			} else {
				System.out.println(horarioExistente);
				flash.addFlashAttribute("error", "Horario no encontrado.");
				return "redirect:/citamedica/mostrar_citamedica_paciente";
			}

			citaMedicaService.save(citamedica);

			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			System.out.println("Datos Guardados");
			return "redirect:/citamedica/mostrar_citamedica_paciente";
		} else {
			flash.addFlashAttribute("error", "El paciente ya no puede agendar citas hoy.");
			return "redirect:/citamedica/mostrar_citamedica_paciente";
		}
	}

	/* -------------- Listar Citas Médicas -------------- */

	@RequestMapping("/listar_citamedica")
	public String verPaginaP(Model model) {
		List<CitaMedica> citas = (List<CitaMedica>) citaMedicaService.getAllCitas();
		model.addAttribute("listCitas", citas);
		model.addAttribute("titulo", "Citas Médicas Registradas");
		return "citamedica/listar_citamedica";
	}

	/* -------------- Listar Citas Médicas Paciente -------------- */

	@RequestMapping("/mostrar_citamedica_paciente")
	public String verCitasPaciente(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		Usuario usuario = usuarioService.findByUsername(username);
		// Se obtiene paciente desde el usuario logueado
		Paciente paciente = pacienteService.getPacienteByUsuario(usuario.getId());
		System.out.println("Datos Guardados" + paciente.getId());

		List<CitaMedica> citas = citaMedicaService.getCitasPorPaciente(paciente.getId());
		model.addAttribute("listCitas", citas);
		model.addAttribute("titulo", "Citas Médicas Registradas");
		return "citamedica/mostrar_citamedica_paciente";
	}

	/* -------------- Reprogramar Cita Médica -------------- */

	@RequestMapping("/editar_citamedica/{id}") // Reprogramar
	public String mostrarReprogramarCitaMedica(@PathVariable("id") int id, Model model, RedirectAttributes flash) {
		CitaMedica citaMedica = citaMedicaService.get(id);
		if (citaMedica == null) {
			flash.addFlashAttribute("error", "No se encontró Cita Médica");
			return "redirect:/citamedica/listar_citamedica";
		} else {

			// List<Roles> roles = rolesService.findAllRoles(); // Asegúrate de tener este
			// método en tu servicio
			DatosEncapsulados citaDTO = new DatosEncapsulados();
			citaDTO.setCita(citaMedica);

			model.addAttribute("citaDTO", citaDTO);

			return "citamedica/editar_citamedica";
		}
	}

	@RequestMapping(value = "/reprogramar_citamedica/{id}", method = RequestMethod.POST)
	public String guardarCitaMedicaReprogramada(@PathVariable("id") int id,
			@ModelAttribute("citaDTO") DatosEncapsulados citaDTO, RedirectAttributes flash, SessionStatus status) {

		CitaMedica citamedica = citaMedicaService.get(id);
		Horario horario = citaDTO.getHorario();

		// No se reprograman Citas Médicas ya Atendidas o Canceladas
		if (citamedica.getEstado() != 1) {
			System.out.println("ESTADO de la cita " + citamedica.getEstado());
			flash.addFlashAttribute("error", "Cita no se puede Reprogramar, verifique el estado.");
			return "redirect:/citamedica/listar_citamedica";
		} else {
			Horario horarioAnterior = horarioService.findById(citamedica.getHorario().getId());
			Horario horarioNuevo = horarioService.findById(horario.getId());
			if (horarioNuevo != null || horarioAnterior != null) {
				// Establecer estado en 0 como Utilizado
				System.out.println("Se debe guardar en estado 0 " + horarioNuevo);

				// Se establece en 1 el anterior como Disponible
				horarioAnterior.setEstado(1);
				// Asigamos el nuevo id de Horario a Cita Médica
				citamedica.setHorario(horarioNuevo);

				horarioNuevo.setEstado(0);

				horarioService.save(horarioAnterior); // Se actualiza el Horario anterior a 1
				horarioService.save(horarioNuevo); // Se actualiza el Horario nuevo a 0

				citaMedicaService.save(citamedica);

			}
		}

		status.setComplete();
		flash.addFlashAttribute("success", "Se reprogramó Cita Médica de forma exitosa.");
		System.out.println("Datos Guardados");
		return "redirect:/citamedica/listar_citamedica";
	}

	/* -------------- Cancelar Cita Médica -------------- */
	@RequestMapping("/cancelar_citamedica/{id}")
	public String cancelarCitaMedica(@PathVariable(name = "id") int id, RedirectAttributes flash,
			SessionStatus status) {
		CitaMedica citamedica = citaMedicaService.get(id);

		// Solo se cancelan Citas Médicas Sin Atender
		if (citamedica.getEstado() != 1) {
			System.out.println("ESTADO de la cita " + citamedica.getEstado());
			flash.addFlashAttribute("error", "Cita no se puede Cancelar, verifique el estado.");
			return "redirect:/citamedica/listar_citamedica";
		} else {
			Horario horarioAnterior = horarioService.findById(citamedica.getHorario().getId());
			Horario horarioNuevo = horarioService.getPorTipo("CANCELADO");
			if (horarioNuevo != null || horarioAnterior != null) {
				// Establecer estado en 3 como Cancelado
				System.out.println("Se debe guardar en estado 0 " + horarioNuevo);

				// Se establece en 1 el anterior como Disponible
				horarioAnterior.setEstado(1);
				// Asigamos el nuevo id de Horario a Cita Médica
				citamedica.setHorario(horarioNuevo);

				// Se cambia a 2 para Cancelarla
				citamedica.setEstado(2);

				horarioService.save(horarioAnterior); // Se actualiza el Horario anterior a 1

				citaMedicaService.save(citamedica);

			}
		}

		status.setComplete();
		flash.addFlashAttribute("success", "Se canceló la Cita Médica de forma exitosa.");
		System.out.println("Datos Guardados");
		return "redirect:/citamedica/listar_citamedica";
	}

	/* -------------- Mostrar Citas Médicas por médico -------------- */

	@RequestMapping("/mostrar_citamedica")
	public String mostrarCitasMedicas(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		Usuario usuario = usuarioService.findByUsername(username);

		// Se obtiene id de Médico logueado
		Medico medico = medicoService.findByUsuarioId(usuario.getId());
		// Obtiene las citas que tiene el medico al día, semana y mes
		List<CitaMedica> listCitasDiarias = citaMedicaService.getCitasPorMedicoDiarias(medico.getId());
		List<CitaMedica> lisCitasSemanales = citaMedicaService.getCitasPorMedicoSemanales(medico.getId());
		List<CitaMedica> listCitasMensuales = citaMedicaService.getCitasPorMedicoMensuales(medico.getId());

		// Fecha actual
		LocalDate hoy = LocalDate.now();
		// Calcular el primer día de la semana (lunes)
		LocalDate lunes = hoy.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

		// Calcular el primer y el último día del mes
		LocalDate primerDiaMes = hoy.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate ultimoDiaMes = hoy.with(TemporalAdjusters.lastDayOfMonth());

		// Añadir las fechas de la semana al modelo
		model.addAttribute("titulo", "Citas Médicas Registradas");
		model.addAttribute("listCitasDiarias", listCitasDiarias);
		model.addAttribute("lisCitasSemanales", lisCitasSemanales);
		model.addAttribute("listCitasMensuales", listCitasMensuales);

		model.addAttribute("hoy", hoy);
		model.addAttribute("lunes", lunes);
		model.addAttribute("primerDiaMes", primerDiaMes);
		model.addAttribute("ultimoDiaMes", ultimoDiaMes);

		return "citamedica/mostrar_citamedica";
	}

	/* -------------- Atender Cita Médica -------------- */

	@RequestMapping("/atender_citamedica/{id}") // Reprogramar
	public String mostrarAtenderCitaMedica(@PathVariable("id") int id, Model model, RedirectAttributes flash) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		Usuario usuario = usuarioService.findByUsername(username);

		// Se obtiene id de Médico logueado
		Medico medico = medicoService.findByUsuarioId(usuario.getId());

		List<Medicamento> medicamentos = medicamentoService.obtenerMedicamentoDisponible();

		CitaMedica citaMedica = citaMedicaService.get(id);
		if (citaMedica == null) {
			flash.addFlashAttribute("error", "No se encontró Cita Médica");
			return "citamedica/mostrar_citamedica";
		} else {

			// List<Roles> roles = rolesService.findAllRoles(); // Asegúrate de tener este
			// método en tu servicio
			DatosEncapsulados citaDTO = new DatosEncapsulados();

			Horario horario = new Horario();
			MedicoHorario medicoHorario = new MedicoHorario();
			Receta receta = new Receta();
			HistorialMedico historia = new HistorialMedico();

			citaDTO.setReceta(receta);
			citaDTO.setCita(citaMedica);
			citaDTO.setHorario(horario);
			citaDTO.setMedicoHorario(medicoHorario);
			citaDTO.setMedico(medico);
			citaDTO.setHistorial(historia);
			
			//Se obtiene historial del paciente que se va atender
			List<HistorialMedico> historialMedico = historialMedicoService.getAllHistorialMedicoPorPaciente(citaDTO.getCita().getPaciente().getId());

	        model.addAttribute("historialMedico", historialMedico);
			model.addAttribute("citaDTO", citaDTO);
			model.addAttribute("medicamentos", medicamentos);

			return "citamedica/atender_citamedica";
		}
	}

	@PostMapping("/guardar_atencion_cita/{id}")
	public String guardarAtencionMedica(@PathVariable("id") int id,
			@ModelAttribute("usuarioDTO") DatosEncapsulados citaDTO, BindingResult result, SessionStatus status,
			Model model, RedirectAttributes flash) {

		// Se extrae la receta
		Receta receta = citaDTO.getReceta();

		// Se extrae la HistoriaMedica
		HistorialMedico historia = citaDTO.getHistorial();

		// Se extrae la HistoriaMedica
		Horario horario = horarioService.findById(citaDTO.getHorario().getId());

		CitaMedica cita = citaMedicaService.get(id);
		System.out.println(" cita que se recupera " + cita.getId() + " Receta a guardar " + receta.getIndicaciones()
				+ " horario que se guardara " + horario.getId() + " historial " + historia.getProximoControl());

		// Verificar que el campo rol no sea vacío
		if (result.hasErrors() || citaDTO.getHorario().getId() == 0) {
			model.addAttribute("error", "Ingrese horario para próximo control.");

			return "redirect:/citamedica/mostrar_citamedica";
		}

		else {

			receta.setFecha(new Date());
			receta.setCitaMedica(cita);
			recetaService.save(receta);

			historia.setProximoControl(horario);
			historia.setCitaMedica(cita);
			historiaMedicoService.save(historia);

			// Establecer horario como ocupado
			horario.setEstado(0);
			horarioService.save(horario);

			// Modificamos el estado de la cita medica a atendida 0
			cita.setEstado(0);
			citaMedicaService.save(cita);
			System.out.println(" cita que se recupera " + cita.getId() + " Receta a guardar " + receta.getIndicaciones()
					+ " horario que se guardara " + horario.getId() + " historial " + historia.getProximoControl());
			status.setComplete();
			flash.addFlashAttribute("success", "El atención médica se registró correctamente.");
			System.out.println("Datos Guardados");
			return "redirect:/citamedica/mostrar_citamedica";
		}

	}

	/* -------------- Cancelar Cita Médica -------------- */
	@RequestMapping("/cancelar_citamedica_paciente/{id}")
	public String cancelarCitaMedicaPaciente(@PathVariable(name = "id") int id, RedirectAttributes flash,
			SessionStatus status) {
		CitaMedica citamedica = citaMedicaService.get(id);

		// Solo se cancelan Citas Médicas Sin Atender
		if (citamedica.getEstado() != 1) {
			System.out.println("ESTADO de la cita " + citamedica.getEstado());
			flash.addFlashAttribute("error", "Cita no se puede Cancelar, verifique el estado.");
			return "redirect:/citamedica/mostrar_citamedica_paciente";
		} else {
			Horario horarioAnterior = horarioService.findById(citamedica.getHorario().getId());
			Horario horarioNuevo = horarioService.getPorTipo("CANCELADO");
			if (horarioNuevo != null || horarioAnterior != null) {
				// Establecer estado en 3 como Cancelado
				System.out.println("Se debe guardar en estado 0 " + horarioNuevo);

				// Se establece en 1 el anterior como Disponible
				horarioAnterior.setEstado(1);
				// Asigamos el nuevo id de Horario a Cita Médica
				citamedica.setHorario(horarioNuevo);

				// Se cambia a 2 para Cancelarla
				citamedica.setEstado(2);

				horarioService.save(horarioAnterior); // Se actualiza el Horario anterior a 1

				citaMedicaService.save(citamedica);

			}
		}

		status.setComplete();
		flash.addFlashAttribute("success", "Se canceló la Cita Médica de forma exitosa.");
		System.out.println("Datos Guardados");
		return "redirect:/citamedica/mostrar_citamedica_paciente";
	}

}
