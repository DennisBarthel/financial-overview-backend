package de.netos.base.rest;

import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.netos.base.util.YearMonthFormatter;

public class YearMonthSerializer extends JsonSerializer<YearMonth> {

	@Inject
	@YearMonthFormatter
	private DateTimeFormatter formatter;

	@Override
	public void serialize(YearMonth value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(formatter.format(value));
	}

}