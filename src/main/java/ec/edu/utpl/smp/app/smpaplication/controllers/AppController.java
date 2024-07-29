package ec.edu.utpl.smp.app.smpaplication.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.edu.utpl.smp.app.smpaplication.models.entities.Usuario;
import ec.edu.utpl.smp.app.smpaplication.models.services.interfaces.IUsuarioService;

@Controller
public class AppController {

	@Autowired
	private IUsuarioService usuarioService;

	/* -------------- Inicio Sistema -------------- */

	@RequestMapping("/")
	public String inicioSistema(Model model) {
		model.addAttribute("titulo", "Sistema de Automatización Hospitalaria Isidro Ayora");

		// Obtener la autenticación actual
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		Usuario usuario = usuarioService.findByUsername(username);

		// Obtener los roles del usuario
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String role = authorities.iterator().next().getAuthority(); // Asumiendo un solo rol por usuario

		// Agregar el rol al modelo
		model.addAttribute("role", role);
		model.addAttribute("usuarioId", usuario.getId());

		return "inicio/inicio_sistema";
	}

	@RequestMapping("menu")
	public String menu(Model model) {
		// Obtener la autenticación actual
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Obtener los roles del usuario
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String role = authorities.iterator().next().getAuthority(); // Asumiendo un solo rol por usuario

		// Agregar el rol al modelo
		model.addAttribute("role", role);

		return "menu";
	}

	@RequestMapping("admin")
	public String admin(Model model) {
		// Obtener la autenticación actual
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Obtener los roles del usuario
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String role = authorities.iterator().next().getAuthority(); // Asumiendo un solo rol por usuario

		// Agregar el rol al modelo
		model.addAttribute("role", role);

		return "admin";
	}

	@RequestMapping("user")
	public String user(Model model) {
		// Obtener la autenticación actual
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Obtener los roles del usuario
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String role = authorities.iterator().next().getAuthority(); // Asumiendo un solo rol por usuario

		// Agregar el rol al modelo
		model.addAttribute("role", role);

		return "user";
	}

}
