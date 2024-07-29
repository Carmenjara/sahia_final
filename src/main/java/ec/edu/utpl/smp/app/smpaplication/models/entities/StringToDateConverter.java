package ec.edu.utpl.smp.app.smpaplication.models.entities;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StringToDateConverter implements Converter<String, Date> {

	private static final String DATE_FORMAT = "yyyy-MM-dd"; // Asegúrate de que este formato coincide con el formato de
															// entrada

	@Override
	public Date convert(String source) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format: " + source, e);
		}
	}
}
