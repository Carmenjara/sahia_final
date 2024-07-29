package ec.edu.utpl.smp.app.smpaplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.utpl.smp.app.smpaplication.models.entities.DatosEncapsulados;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Paciente;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Persona;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Roles;
import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.entities.UsuarioRol;
import ec.edu.utpl.smp.app.smpaplication.models.entities.UsuarioRolId;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPacienteService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IPersonaService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IRolesService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioRolService;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioService;

@Controller
public class UsuarioFueraController {

	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IPersonaService personaService;

	@Autowired
	private IRolesService rolesService;

	@Autowired
	private IUsuarioRolService usuarioRolService;

	@Autowired
	private IPacienteService pacienteService;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	/*@GetMapping("/")
    public String mostrarFormularioLogin(Model model) {
		
		DatosEncapsulados usuarioDTO = new DatosEncapsulados();
		Persona persona = new Persona();
		usuarioDTO.setPersona(persona);

		Roles rol = rolesService.findById(4);

		model.addAttribute("rol", rol);
		model.addAttribute("usuarioDTO", usuarioDTO);
        return "login";
    }*/
	
	@PostMapping("/usuarios/guardar_usuario_fuera")
	public String guardarUsuario(@ModelAttribute("usuarioDTO") DatosEncapsulados usuarioDTO, BindingResult result,
			SessionStatus status, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("showRegisterModal", true);
			return "index";
		}

		Persona persona = usuarioDTO.getPersona();
		Usuario usuario = usuarioDTO.getUsuario();

		UsuarioRol usuarioRol = usuarioDTO.getUserRol();

		// Verificar que el campo rol no sea vacío
		if (result.hasErrors() || usuarioDTO.getUserRol().getRol().getId() == 0) {
			if (usuarioDTO.getUserRol().getRol().getId() == 0) {
				Roles rol = rolesService.findById(4);
				model.addAttribute("rol", rol);
				model.addAttribute("error", "Seleccione un rol válido.");
			}
			return "index";
		}

		else

		// si usuario existe se redirecciona al mismo formulario con la data
		// preingresada
		if (usuarioService.existeUsuario(usuario.getUsername())) {

			model.addAttribute("error", "El nombre de usuario ya está en uso. Pruebe con otro.");
			model.addAttribute("usuarioDTO", usuarioDTO);

			Roles rol = rolesService.findById(4);
			model.addAttribute("rol", rol);
			flash.addFlashAttribute("error", "El nombre de usuario ya está en uso.");
			return "index"; // Mostrar mensaje de error en la misma página
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

			if (usuarioRol.getRol().getId() == 4) {
				Paciente pacienteNuevo = new Paciente();
				pacienteNuevo.setPersona(persona);
				pacienteService.save(pacienteNuevo);

			}

			status.setComplete();
			flash.addFlashAttribute("success",
					"El usuario para " + persona.getNombres() + " se registró correctamente.");
			System.out.println("Datos Guardados");
			return "redirect:/";
		}
	}

}
