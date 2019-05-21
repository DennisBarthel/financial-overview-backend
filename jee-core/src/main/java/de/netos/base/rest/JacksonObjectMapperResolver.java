package de.netos.base.rest;

import javax.inject.Inject;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class JacksonObjectMapperResolver implements ContextResolver<ObjectMapper> {

	@Inject
	private SerializerModule serializerModule;

	@Override
	public ObjectMapper getContext(Class<?> type) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(serializerModule);

		return mapper;
	}

}
