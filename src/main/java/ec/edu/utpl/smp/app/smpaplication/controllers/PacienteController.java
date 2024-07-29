package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
import ec.edu.utpl.smp.app.smpaplication.models.entities.Paciente;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPacienteService;

@Controller
@RequestMapping("/pacientes")
@SessionAttributes("pacienteDTO")
public class PacienteController {

	@Autowired
	private IPacienteService pacienteService;

	/* -------------- Gestión Paciente -------------- */

	/* -------------- Inicio Paciente -------------- */

	// @Secured("ROLE_USER")
	@RequestMapping("/inicio_pacientes")
	public String inicioPacientes(Model model) {
		model.addAttribute("titulo", "Gestión Pacientes");
		return "paciente/inicio_paciente";
	}

	/* -------------- Crear Paciente -------------- */

	// @Secured("ROLE_USER")
	@RequestMapping("/crear_paciente")
	public String crearPaciente(Model model) {
		DatosEncapsulados pacienteDTO = new DatosEncapsulados();
		Paciente paciente = new Paciente();
		pacienteDTO.setPaciente(paciente);

		/// List<Item> listRangos = itemService.listaItems(id);
		model.addAttribute("titulo", "Registrar Paciente");
		model.addAttribute("pacienteDTO", pacienteDTO);
		// model.addAttribute("items_rango", listRangos);

		return "paciente/crear_paciente";
	}

	/* -------------- Listar Paciente -------------- */

	
	@RequestMapping("/listar_pacientes")
	public String listarPacientes(Model model) {
		model.addAttribute("titulo", "Pacientes Registrados");
		model.addAttribute("listPacientes", pacienteService.getAllPersonas());

		return "paciente/listar_paciente";
	}

	/* -------------- Guardar Paciente -------------- */

	// @Secured("ROLE_USER")
	@RequestMapping(value = "/guardar_paciente", method = RequestMethod.POST)
	public String guardarPaciente(@ModelAttribute("personal") DatosEncapsulados pacienteDTO, RedirectAttributes flash,
			SessionStatus status) {

		Paciente persona = pacienteDTO.getPaciente();
		persona.setEdad(0);

		/*
		 * String mensajeFlash = (pacienteDTO.getIdpersonal() != null) ?
		 * "Personal Editado Exitosamente!" : "Personal Registrado Exitosamente!";
		 * 
		 * personalService.save(pacienteDTO);
		 */
		status.setComplete();
		// flash.addFlashAttribute("success", mensajeFlash);
		System.out.println("Datos Guardados");
		return "redirect:/personal/listar_personal";
	}

	/* -------------- Editar Paciente -------------- */

	
	@RequestMapping(value = "/editar_personal/{idpersonal}")
	public String editar(@PathVariable(value = "idpersonal") long idpersonal, Map<String, Object> model,
			RedirectAttributes flash) {

		long idrango = 1;
		//Personal personal = null;

		if (idpersonal > 0) {
			//personal = personalService.get(idpersonal);
			//if (personal == null) {
				flash.addFlashAttribute("error", "El ID del Personal que Desea Editar No Existe en la Base de Datos!");
				return "redirect:/personal//listar_personal";
			//}
		} else {
			flash.addFlashAttribute("error", "El ID del Personal que Desea Editar No Puede Ser Cero!");
			return "redirect:/personal/listar_personal";
		}

		//List<Item> listRangos = itemService.listaItems(idrango);
		//model.put("titulo", "Editar Personal Policial");
		//model.put("personal", personal);
		//model.put("items_rango", listRangos);

		//return "personal/editar_personal";
	}

	/* -------------- Eliminar Personal -------------- */

	@RequestMapping("/eliminar_personal/{idpersonal}")
	public String eliminarPersonal(@PathVariable(name = "idpersonal") long idpersonal, RedirectAttributes flash) {

		if (idpersonal > 0) {
			//personalService.delete(idpersonal);
			flash.addFlashAttribute("success", "Personal Eliminado Exitosamente!");
		}
		return "redirect:/personal/listar_personal";
	}

}
