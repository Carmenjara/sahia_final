package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Horario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IHorarioService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioService;


@Controller
@RequestMapping("/horario")
@SessionAttributes("horario")
public class HorarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IHorarioService horarioService;

	@RequestMapping("/registrar_horario")
	public String resgistrarHorario(Model model) {
		model.addAttribute("titulo", "Registrar Horario");
		model.addAttribute("listUsuarios", usuarioService.findAllMedicos());
		return "horario/registrar_horario";
	}
	
	@RequestMapping("/listar_medicos")
	public String listarMedicos(Model model) {
		model.addAttribute("titulo", "Registrar Horario");
		model.addAttribute("listUsuarios", usuarioService.findAllMedicos());
		return "medico/listar_medico";
	}
	
	@PostMapping("/crear")
	public String crearHorario(@RequestParam("fecha") String fechaStr, @RequestParam("medicoId") Integer medicoId,
			RedirectAttributes flash) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date fecha = dateFormat.parse(fechaStr);
			horarioService.crearHorariosPorFecha(fecha, medicoId);
			flash.addFlashAttribute("success", "Horario creado correctamente para la fecha: " + fechaStr);
			return "redirect:/horario/registrar_horario";
		} catch (ParseException e) {
			return "Error al crear el horario: " + e.getMessage();
		}
	}

	@RequestMapping("/listar_horario/{id}")
	public String mostarListaHorariosMedico(@PathVariable("id") int id, Model model, RedirectAttributes flash) {
		Usuario usuario = usuarioService.findById(id);
		if (usuario == null) {
			return "redirect:/horario/registrar_horario";
		}
		model.addAttribute("usuarioDTO", usuario.getPersona());
		model.addAttribute("idUsuario", usuario.getId());
		model.addAttribute("titulo", "Registrar Horario");
		model.addAttribute("listHorarios", horarioService.getHorarioPorUsuario(usuario.getId()));

		return "horario/listar_horario";
	}

	// Deshabilitar horario
	@RequestMapping(value = "/deshabilitar_horario/{id}/{idUsuario}")
	public String deshabilitarHorario(@PathVariable(name = "id") Integer id,
			@PathVariable(name = "idUsuario") Integer idUsuario, RedirectAttributes flash, SessionStatus status) {

		// Buscar el horario existente usando su ID
		Horario horarioExistente = horarioService.findById(id);

		if (horarioExistente.getEstado() == 1) {
			// Establecer estado en 2 como Deshabilitado
			System.out.println("Se debe guardar en estado 2 " + horarioExistente);
			horarioExistente.setEstado(2);

			horarioService.save(horarioExistente); // Solo el campo estado se actualiza

			flash.addFlashAttribute("success", "Horario deshabilidado exitosamente!");
			System.out.println("Datos Guardados");

		} else if (horarioExistente.getEstado() == 0) {
			System.out.println(horarioExistente);
			flash.addFlashAttribute("error", "Horario se encuentra vinculado a Cita Médica");
			return "redirect:/horario/listar_horario/" + idUsuario;
		} else {
			flash.addFlashAttribute("error", "Horario ya está deshabilitado");
			return "redirect:/horario/listar_horario/" + idUsuario;

		}

		status.setComplete();
		return "redirect:/horario/listar_horario/" + idUsuario;
	}

}
