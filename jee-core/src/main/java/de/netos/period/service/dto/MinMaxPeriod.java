package de.netos.period.service.dto;

import java.time.YearMonth;

public class MinMaxPeriod {
	private final YearMonth minDate;
	private final YearMonth maxDate;

	public MinMaxPeriod(YearMonth minDate, YearMonth maxDate) {
		this.minDate = minDate;
		this.maxDate = maxDate;
	}

	public YearMonth getMinDate() {
		return minDate;
	}

	public YearMonth getMaxDate() {
		return maxDate;
	}
}
