package de.netos.period.service;

import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import de.netos.base.util.YearMonthFormatter;

public class PeriodMapper {

	@Inject
	@YearMonthFormatter
	private DateTimeFormatter formatter;

}
