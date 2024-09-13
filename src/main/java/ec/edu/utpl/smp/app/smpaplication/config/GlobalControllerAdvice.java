package ec.edu.utpl.smp.app.smpaplication.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collection;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ModelAttribute
	public void addAttributes(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			String role = authorities.isEmpty() ? "" : authorities.iterator().next().getAuthority();

			model.addAttribute("role", role);
			// Puedes agregar más atributos si es necesario
			// Ejemplo: model.addAttribute("username", username);
		}
	}
}
