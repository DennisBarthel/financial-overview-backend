package de.netos.base.formatter;

import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FormatterFactory {

	private DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
	
	@Bean
	@FormatterType("yearMonth")
	public DateTimeFormatter createYearMonthFormatter() {
		return yearMonthFormatter;
	}
}
