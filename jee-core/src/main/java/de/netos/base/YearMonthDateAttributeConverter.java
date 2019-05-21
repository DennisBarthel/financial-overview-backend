package de.netos.base;

import java.sql.Date;
import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;

import javax.persistence.AttributeConverter;

public class YearMonthDateAttributeConverter implements AttributeConverter<YearMonth, Date> {

	@Override
	public Date convertToDatabaseColumn(YearMonth attribute) {
		return Date.valueOf(attribute.atDay(1));
	}

	@Override
	public YearMonth convertToEntityAttribute(Date dbData) {
		return YearMonth.from(Instant.ofEpochMilli(dbData.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
	}

}
