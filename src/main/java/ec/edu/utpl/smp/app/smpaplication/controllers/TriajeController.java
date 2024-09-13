package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.utpl.smp.app.smpaplication.models.entities.DatosEncapsulados;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Especialidad;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Paciente;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Triaje;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IEspecialidadService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPacienteService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.ITriajeService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping("/triaje")
@SessionAttributes("triaje")
public class TriajeController {

	@Autowired
	private ITriajeService triajeService;

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPacienteService pacienteService;


	/* -------------- Crear Triaje-------------- */

	@RequestMapping("/crear_triaje_emergencia")
	public String mostrarCrearTriaje(Map<String, Object> model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		Usuario usuario = usuarioService.findByUsername(username);

		// Se obtiene id de paciente que se encuentra logueado
		Paciente paciente = pacienteService.getPacienteByUsuario(usuario.getId());
		
		DatosEncapsulados triajeDTO = new DatosEncapsulados();
		Triaje triaje = new Triaje();
		triajeDTO.setTriaje(triaje);
		triajeDTO.setPaciente(paciente);

		model.put("titulo", "Registrar Triaje");
		model.put("triajeDTO", triajeDTO);

		return "triaje/crear_triaje";
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
	
	/* -------------- Guardar Especialidad -------------- */

	@RequestMapping(value = "/crear_triaje", method = RequestMethod.POST)
	public String guardarTriaje(@ModelAttribute("triajeDTO") DatosEncapsulados triajeDTO,
			RedirectAttributes flash, SessionStatus status) {

		Triaje triaje = triajeDTO.getTriaje();
		Paciente paciente =triajeDTO.getPaciente();

		if (triaje == null) {
			flash.addFlashAttribute("error", "Error al crear Triaje.");
			return "redirect:/triaje/crear_triaje_emergencia";
		} else {

			
			triaje.setFechaCreacion(new Date());
			triaje.setFechaActualizacion(new Date());
			triaje.setPaciente(paciente);
			triajeService.save(triaje);
			status.setComplete();
			flash.addFlashAttribute("success", "Triaje creado exitosamente.");
		}

		System.out.println("Datos Guardados");

		return "redirect:/triaje/listar_emergencias";
	}
	
	
	/* -------------- Listar Emergencias -------------- */

	@RequestMapping("/listar_emergencias")
	public String verEmergencias(Model model) {

		model.addAttribute("emergencias", triajeService.getAllTriajes());
		model.addAttribute("titulo", "Emergencias Registradas");
		return "triaje/listar_triaje";
	}
	
	/* -------------- Editar Triaje -------------- */

	@RequestMapping("/editar_triaje/{id}")
	public String mostrarEditarTriaje(@PathVariable("id") int id, Model model, RedirectAttributes flash) {
		Triaje triaje = triajeService.get(id);
		if (triaje == null) {
			flash.addFlashAttribute("error", "No se encontró Triaje.");
			return "redirect:/triaje/listar_emergencias";
		} else {
			DatosEncapsulados triajeDTO = new DatosEncapsulados();
			triajeDTO.setTriaje(triaje);

			model.addAttribute("triajeDTO", triajeDTO);

			return "triaje/editar_triaje";
		}
	}
	
	@RequestMapping(value = "/guardar_triaje_editado/{id}", method = RequestMethod.POST)
	public String guardarTriajeEditado(@PathVariable("id") int id,
			@ModelAttribute("triajeDTO") DatosEncapsulados triajeDTO, RedirectAttributes flash,
			SessionStatus status, Model model) {

		Triaje triaje = triajeDTO.getTriaje();
		Triaje triajeAnterior = triajeService.get(id);
		if (triaje == null) {
			flash.addFlashAttribute("error", "Error al guardar Triaje.");
			return "redirect:/triaje/listar_emergencias";
		}else {
		

			triaje.setFechaActualizacion(new Date());
			triaje.setFechaCreacion(triajeAnterior.getFechaCreacion());
			triajeService.save(triaje);
		}

		status.setComplete();
		flash.addFlashAttribute("success", "Se editó el Triaje de forma exitosa.");
		System.out.println("Datos Guardados");
		return "redirect:/triaje/listar_emergencias";
	}

}
