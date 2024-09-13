package ec.edu.utpl.smp.app.smpaplication.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ec.edu.utpl.smp.app.smpaplication.LoginSuccessHandler;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	String[] resources = new String[] { "/include/**", "/bootstrap/**", "/css/**", "/fontawesome/**", "/img/**",
			"/js/**", "/jquery/**", "/datatables/**", "/layer" };

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private LoginSuccessHandler successHandler;

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeRequests().antMatchers(resources).permitAll()
				// .antMatchers("/aut/**").permitAll()
				.antMatchers("/citamedica/**").hasAnyRole("PACIENTE", "MEDICO","ADMIN")
				.antMatchers("/medico/**").hasAnyRole("PACIENTE", "MEDICO","ADMIN")
				.antMatchers("/pacientes/**").hasAnyRole("PACIENTE", "MEDICO","ADMIN")
				.antMatchers("/consultorio/**").hasAnyRole("PACIENTE", "MEDICO","ADMIN")
				.antMatchers("/especialidad/**").hasAnyRole("PACIENTE", "MEDICO","ADMIN")
				.antMatchers("/usuarios/**").hasAnyRole("PACIENTE", "MEDICO","ADMIN")
				.antMatchers("/horario/**").hasAnyRole("PACIENTE", "MEDICO","ADMIN")
				.anyRequest().authenticated().and().formLogin()
				.successHandler(successHandler).loginPage("/login").permitAll().and().logout().permitAll().and()
				.exceptionHandling().accessDeniedPage("/error_403");
		// http.csrf().disable();
	}

	@Autowired
	public void configureGobal(AuthenticationManagerBuilder builder) throws Exception {

		builder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder)
				// .usersByUsernameQuery("select username, password, persona_id from Usuario
				// where username=?");
				.usersByUsernameQuery("select username, password, enabled from Usuario where username = ?")
				.authoritiesByUsernameQuery(
						"select u.username, CONCAT('ROLE_', r.nombre) from Rol r, Usuario u, Usuario_Rol ur where ur.usuario_id=u.id and ur.rol_id=r.id and u.username=?");
	}

	// Establecer formato para la fechaNacimiento
	/*
	 * public void addFormatters(FormatterRegistry registry) {
	 * registry.addFormatterForFieldType(Date.class, new
	 * DateFormatter("yyyy-MM-dd")); }
	 */

}
