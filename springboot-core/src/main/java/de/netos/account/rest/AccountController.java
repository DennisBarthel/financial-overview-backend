package de.netos.account.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.netos.account.AccountDetailsDTO;
import de.netos.account.AccountOverviewDTO;
import de.netos.account.CreateAccountDTO;
import de.netos.account.ModifyAccountDTO;
import de.netos.account.exception.AccountException;
import de.netos.account.service.AccountService;

@RestController
@RequestMapping(
		path = "/account",
		consumes = "application/json",
		produces = "application/json")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@GetMapping(path = "/", produces = "application/json")
	public List<AccountOverviewDTO> getAccounts() {
		return accountService.getAccounts();
	}
	
	@PostMapping("/create")
	public void addAccount(@RequestBody CreateAccountDTO createAccount) throws AccountException {
		accountService.createAccount(createAccount);
	}
	
	@PutMapping("/modify/{accountId}")
	public void modifyAccount(
			@PathVariable("accountId") String accountId,
			@RequestBody ModifyAccountDTO modifyAccount) throws AccountException {
		accountService.modifyAccount(accountId, modifyAccount);
	}
	
	@DeleteMapping("/delete/{accountId}")
	public void deleteAccount(@PathVariable("accountId") String accountId) throws AccountException {
		accountService.deleteAccount(accountId);
	}
	
	@GetMapping("/details/{accountId}")
	public AccountDetailsDTO getAccountDetails(@PathVariable("accountId") String accountId) throws AccountException {
		return accountService.getAccountDetails(accountId);
	}
}
