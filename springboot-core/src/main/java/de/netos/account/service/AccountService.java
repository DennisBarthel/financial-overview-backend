package de.netos.account.service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.netos.account.AccountDTO;
import de.netos.account.AccountDetailsDTO;
import de.netos.account.AccountOverviewDTO;
import de.netos.account.CreateAccountDTO;
import de.netos.account.ModifyAccountDTO;
import de.netos.account.entity.Account;
import de.netos.account.exception.AccountException;

@Service
public class AccountService {

	@Autowired
	private AccountRepositoryCustom accountRepository;
	
	@Autowired
	private AccountMapper mapper;
	
	public void createAccount(CreateAccountDTO createAccount) throws AccountException {
		Account account = mapper.mapJSONToEntity(createAccount);
		accountRepository.persistAccount(account);
	}
	
	public List<AccountOverviewDTO> getAccounts(String owner) {
		List<AccountOverviewDTO> resultList = new ArrayList<>();

		List<AccountDTO> allAccounts = accountRepository.getAllAccountsByOwner(owner);
		for (AccountDTO accountDTO : allAccounts) {
			resultList.add(new AccountOverviewDTO(accountDTO,
					0d, YearMonth.now()));
		}
		return resultList;
	}

	public void modifyAccount(String accountId, ModifyAccountDTO modifyAccount) throws AccountException {
		Account account = accountRepository.getAccountByAccountId(accountId);
		mapper.mapJSONToEntity(account, modifyAccount);
		
		accountRepository.persistAccount(account);
	}

	public void deleteAccount(String accountId) throws AccountException {
		accountRepository.deleteAccountByAccountId(accountId);
	}

	public AccountDetailsDTO getAccountDetails(String accountId) {
		return null;
	}
}
