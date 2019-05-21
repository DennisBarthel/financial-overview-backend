package de.netos.period.service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.netos.base.formatter.FormatterType;
import de.netos.period.PeriodDTO;
import de.netos.period.exception.PeriodException;
import de.netos.period.exception.PeriodException.ErrorMessage;

@Service
public class PeriodService {
	
	@Autowired
	@FormatterType("yearMonth")
	private DateTimeFormatter formatter;

	public List<PeriodDTO> getPeriods(String accountid, String from, String to) throws PeriodException {
		Optional<YearMonth> fromYearMonth = parseToYearMonth(from);
		Optional<YearMonth> toYearMonth = parseToYearMonth(to);
		
		return Collections.emptyList();
	}
	
	private Optional<YearMonth> parseToYearMonth(String input) throws PeriodException {
		if (input != null) {
			try {
				return Optional.ofNullable(YearMonth.parse(input, formatter));
			} catch (DateTimeParseException e) {
				throw new PeriodException(ErrorMessage.INVALID_TIME_PERIOD, String.format("Invalid input [%s]", input));
			}
		} else {
			return Optional.empty();
		}
	}
}
