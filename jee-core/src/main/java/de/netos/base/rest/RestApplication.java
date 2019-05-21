package de.netos.base.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@ApplicationPath("/rest")
@OpenAPIDefinition(
	info = @Info(title = "My REST API", version = "1.0"))
public class RestApplication extends Application {
}
