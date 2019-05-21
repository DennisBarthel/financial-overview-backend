package de.netos.account.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.netos.account.AccountDTO;
import de.netos.account.AccountDetailsDTO;
import de.netos.account.AccountOverviewDTO;
import de.netos.account.CreateAccountDTO;
import de.netos.account.ModifyAccountDTO;
import de.netos.account.exception.AccountException;
import de.netos.account.service.AccountService;
import de.netos.base.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/v1/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {

	@Inject
	private AccountService accountService;

	@GET
	@Path("/")
	@Operation(summary = "Get a list of all accounts", description = "Get a list of all available accounts", responses = {
			@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDTO.class)), description = "List of all available accounts") })

	public Response getAccounts() {
		List<AccountOverviewDTO> accounts = accountService.getAccounts();

		return Response.ok(accounts).build();
	}

	@POST
	@Path("/create")
	@Operation(summary = "Add a new account", description = "", responses = {
			@ApiResponse(content = @Content(mediaType = "application/json")),
			@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)), responseCode = "400", description = "Account does all ready exist") })
	public Response addAccount(CreateAccountDTO accountJson) throws AccountException {
		accountService.createAccount(accountJson);
		return Response.ok().build();
	}

	@PUT
	@Path("/modify/{accountId}")
	public Response modifyAccount(@PathParam("accountId") String accountId, ModifyAccountDTO modifyAccount)
			throws AccountException {
		accountService.modifyAccount(accountId, modifyAccount);
		return Response.ok().build();
	}

	@DELETE
	@Path("/delete/{accountId}")
	@Operation(summary = "Delete an account by accountId", description = "Delete an account by accountId")
	public Response deleteAccount(
			@Parameter(description = "ID of the account that should be deleted", required = true) @PathParam("accountId") String accountId)
			throws AccountException {
		accountService.deleteAccount(accountId);
		return Response.ok().build();
	}

	@GET
	@Path("/details/{accountId}")
	@Operation(summary = "Get details of an account by accountId", description = "Returns the details of an account if the accountId is valid.", responses = {
			@ApiResponse(description = "The Account", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDetailsDTO.class))),
			@ApiResponse(responseCode = "400", description = "Invalid or unknown accountId") })
	public Response getAccountDetails(
			@Parameter(description = "ID of the account that needs to be fetched", required = true) @PathParam("accountId") String accountId)
			throws AccountException {
		AccountDetailsDTO detailsJson = accountService.getAccountByAccountId(accountId);
		return Response.ok(detailsJson).build();
	}
}
