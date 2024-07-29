package ec.edu.utpl.smp.app.smpaplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ec.edu.utpl.smp.app.smpaplication.models.entities.StringToDateConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final StringToDateConverter stringToDateConverter;

	public WebConfig(StringToDateConverter stringToDateConverter) {
		this.stringToDateConverter = stringToDateConverter;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(stringToDateConverter);
	}
}
