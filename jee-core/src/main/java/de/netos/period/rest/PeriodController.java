package de.netos.period.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.netos.account.exception.AccountException;
import de.netos.period.CreatePeriodDTO;
import de.netos.period.ModifyPeriodDTO;
import de.netos.period.PeriodDTO;
import de.netos.period.exception.PeriodException;
import de.netos.period.service.PeriodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/v1/period")
@Produces(MediaType.APPLICATION_JSON)
public class PeriodController {

	@Inject
	private PeriodService periodService;

	@GET
	@Path("/{accountId}")
	@Operation(summary = "Get a lit of periods for a given account", description = "")
	public Response getPeriods(@Parameter(description = "") @PathParam("accountId") String accountId,
			@Parameter(description = "") @QueryParam("fromDate") String fromDate,
			@Parameter(description = "") @QueryParam("toDate") String toDate) throws PeriodException {
		List<PeriodDTO> periods = periodService.getPeriods(accountId, fromDate, toDate);
		return Response.ok(periods).build();
	}

	@POST
	@Path("/")
	@Operation(summary = "Add a new period", description = "Add a new period for an account")
	public Response addTransaction(CreatePeriodDTO period) throws AccountException, PeriodException {
		periodService.addPeriod(period);
		return Response.ok().build();
	}

	@PUT
	@Path("/update/{periodId}")
	public Response modifyTransaction(@PathParam("periodId") String periodId, ModifyPeriodDTO period)
			throws PeriodException {
		periodService.modifyPeriod(periodId, period);
		return Response.ok().build();
	}

	@DELETE
	@Path("/delete/{periodId}")
	public Response deletePeriod(@PathParam("periodId") String periodId) throws PeriodException {
		periodService.deletePeriod(periodId);
		return Response.ok().build();
	}
}
