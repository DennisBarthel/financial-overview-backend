package de.netos.base.util;

import java.time.format.DateTimeFormatter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class FormatterFactory {

	private DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

	@Produces
	@YearMonthFormatter
	public DateTimeFormatter createYearMonthFormatter() {
		return yearMonthFormatter;
	}
}
