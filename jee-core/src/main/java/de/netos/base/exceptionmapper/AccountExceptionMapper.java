package de.netos.base.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import de.netos.account.exception.AccountException;
import de.netos.base.ErrorResponse;

@Provider
public class AccountExceptionMapper implements ExceptionMapper<AccountException> {

	@Override
	public Response toResponse(AccountException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getErrorMessage().name(), exception.getMessage());

		return Response.status(Status.BAD_REQUEST).entity(errorResponse).build();
	}

}
