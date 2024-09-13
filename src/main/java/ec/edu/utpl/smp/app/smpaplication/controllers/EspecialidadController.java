package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.utpl.smp.app.smpaplication.models.entities.DatosEncapsulados;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Especialidad;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IEspecialidadService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
@RequestMapping("/especialidad")
@SessionAttributes("especialidad")
public class EspecialidadController {

	@Autowired
	private IEspecialidadService especialidadService;

	@Autowired
	private IUsuarioService usuarioService;

	/* -------------- Gestión Especialidades -------------- */

	/* -------------- Inicio Especialidades -------------- */

	@RequestMapping("/inicio_especialidad")
	public String verPaginaC(Model model) {
		model.addAttribute("titulo", "Gestión de Especialidades");
		return "especialidad/inicio_especialidad";
	}

	/* -------------- Listar Especialidades -------------- */

	@RequestMapping("/listar_especialidad")
	public String verPaginaP(Model model) {

		model.addAttribute("especialidades", especialidadService.getAllEspecialidades());
		model.addAttribute("titulo", "Especialidades Registradas");
		System.out.println("especialidadService.getAllEspecialidades()" + especialidadService.getAllEspecialidades());
		return "especialidad/listar_especialidad";
	}

	/* -------------- Inhabilitar Especialidad -------------- */
	@RequestMapping("/inhabilitar_especialidad/{id}")
	public String inhabilitarEspecialidad(@PathVariable(name = "id") int id, RedirectAttributes flash,
			SessionStatus status) {
		Especialidad especialidad = especialidadService.findByIdEspecialidad(id);

		// Solo se inhabilitan Especialidades que no estan siendo usados
		if (especialidad.getEstado() == 0) {
			
			
			Usuario usuario = new Usuario();

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null) {
				Object principal = authentication.getPrincipal();
				if (principal instanceof UserDetails) {
					UserDetails userDetails = (UserDetails) principal;
					usuario = usuarioService.findByUsername(userDetails.getUsername());
				}
			}
			// Establecer estado en 2 como Inhabilitado
			especialidad.setEstado(2);
			especialidad.setUsuario(usuario);
			especialidad.setFechaModificacion(new Date());
			especialidadService.save(especialidad);
			
			flash.addFlashAttribute("error", "Especialidad se inhabilitó, aunque se encuentra asociada a Médico.");
			return "redirect:/especialidad/listar_especialidad";
		} else if (especialidad.getEstado() == 2) {
			flash.addFlashAttribute("error", "Especialidad ya se encuentra inhabilitada.");
			return "redirect:/especialidad/listar_especialidad";
		} else {
			Usuario usuario = new Usuario();

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null) {
				Object principal = authentication.getPrincipal();
				if (principal instanceof UserDetails) {
					UserDetails userDetails = (UserDetails) principal;
					usuario = usuarioService.findByUsername(userDetails.getUsername());
				}
			}
			// Establecer estado en 2 como Inhabilitado
			especialidad.setEstado(2);
			especialidad.setUsuario(usuario);
			especialidad.setFechaModificacion(new Date());
			especialidadService.save(especialidad);
		}

		status.setComplete();
		flash.addFlashAttribute("success", "Se inhabilitó la Especialidad de forma exitosa.");
		System.out.println("Datos Guardados");
		return "redirect:/especialidad/listar_especialidad";
	}

	/* -------------- Editar Especialidad -------------- */

	@RequestMapping("/editar_especialidad/{id}")
	public String mostrarEditarEspecialidad(@PathVariable("id") int id, Model model, RedirectAttributes flash) {
		Especialidad especialidad = especialidadService.get(id);
		if (especialidad == null) {
			flash.addFlashAttribute("error", "No se encontró Especialidad.");
			return "redirect:/especialidad/listar_especialidad";
		} else {
			DatosEncapsulados especialidadDTO = new DatosEncapsulados();
			especialidadDTO.setEspecialidad(especialidad);

			model.addAttribute("especialidadDTO", especialidadDTO);

			return "especialidad/editar_especialidad";
		}
	}

	@RequestMapping(value = "/guardar_especialidad_editada/{id}", method = RequestMethod.POST)
	public String guardarEspecialidadEditado(@PathVariable("id") int id,
			@ModelAttribute("citaDTO") DatosEncapsulados especialidadDTO, RedirectAttributes flash,
			SessionStatus status, Model model) {

		Especialidad especialidad = especialidadDTO.getEspecialidad();
		Especialidad especialidadAnterior = especialidadService.findByIdEspecialidad(id);
		if (especialidad == null) {
			flash.addFlashAttribute("error", "Error al guardar Especialidad.");
			return "redirect:/especialidad/listar_especialidad";
		}
		
		// Solo se inhabilitan especialidades que no estan siendo usadas
		if (especialidadAnterior.getEstado() == 0 && especialidad.getEstado() != 0) {
			
			DatosEncapsulados especialidadDTONew = new DatosEncapsulados();
			especialidadDTONew.setEspecialidad(especialidad);
	        model.addAttribute("especialidadDTO", especialidadDTONew);
	        model.addAttribute("error", "Especialidad no se puede inhabilitar, se encuentra asociada a Médico.");

			return "especialidad/editar_especialidad";

		}else if ((especialidadAnterior.getEstado() == 2 || especialidadAnterior.getEstado() == 1) && especialidad.getEstado() == 0) {

			DatosEncapsulados especialidadDTONew = new DatosEncapsulados();
			especialidadDTONew.setEspecialidad(especialidad);
	        model.addAttribute("especialidadDTO", especialidadDTONew);
	        model.addAttribute("error", "Especialidad no se puede indisponer directamente, se debe asociar a un Médico.");

			return "especialidad/editar_especialidad";

		}  
		else {
			// Se establece el usuario que hace el cambio, el logueado
			Usuario usuario = new Usuario();

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null) {
				Object principal = authentication.getPrincipal();
				if (principal instanceof UserDetails) {
					UserDetails userDetails = (UserDetails) principal;
					usuario = usuarioService.findByUsername(userDetails.getUsername());
				}
			}

			especialidad.setFechaModificacion(new Date());
			especialidad.setUsuario(usuario);
			especialidad.setFechaCreacion(especialidadAnterior.getFechaCreacion());
			especialidadService.save(especialidad);
		}

		status.setComplete();
		flash.addFlashAttribute("success", "Se editó la Especialidad de forma exitosa.");
		System.out.println("Datos Guardados");
		return "redirect:/especialidad/listar_especialidad";
	}

	/* -------------- Crear Especialidad-------------- */

	@RequestMapping("/crear_especialidad")
	public String mostrarCrearEspecialidad(Model model) {
		DatosEncapsulados especialidadDTO = new DatosEncapsulados();
		Especialidad especialidad = new Especialidad();
		especialidadDTO.setEspecialidad(especialidad);

		model.addAttribute("titulo", "Registrar Especialidad");
		model.addAttribute("especialidadDTO", especialidadDTO);

		return "especialidad/crear_especialidad";
	}
	/* -------------- Guardar Especialidad -------------- */

	@RequestMapping(value = "/crear_especialidad", method = RequestMethod.POST)
	public String guardarEspecialidad(@ModelAttribute("consultorioDTO") DatosEncapsulados especialidadDTO,
			RedirectAttributes flash, SessionStatus status) {

		Especialidad especialidad = especialidadDTO.getEspecialidad();
		Usuario usuario = new Usuario();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				usuario = usuarioService.findByUsername(userDetails.getUsername());
			}
		}

		if (especialidad == null) {
			flash.addFlashAttribute("error", "Error al crear Especialidad.");
			return "redirect:/consultorio/listar_consultorio";
		} else {

			especialidad.setEstado(1);
			especialidad.setFechaCreacion(new Date());
			especialidad.setFechaModificacion(new Date());
			especialidad.setUsuario(usuario);
			especialidadService.save(especialidad);
			status.setComplete();
			flash.addFlashAttribute("success", "Especialidad creada exitosamente.");
		}

		System.out.println("Datos Guardados");

		return "redirect:/especialidad/listar_especialidad";
	}

}
