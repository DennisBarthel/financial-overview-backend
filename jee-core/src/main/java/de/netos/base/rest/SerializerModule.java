package de.netos.base.rest;

import java.time.YearMonth;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class SerializerModule extends SimpleModule {

	private static final long serialVersionUID = -1213386099970244019L;
	
	@Inject
	private YearMonthSerializer testS;

	@PostConstruct
	public void create() {
		this.addSerializer(YearMonth.class, testS);
	}
}
