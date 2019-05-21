package de.netos.account.service;

import java.util.List;

import de.netos.account.AccountDTO;
import de.netos.account.entity.Account;
import de.netos.account.exception.AccountException;

public interface AccountRepositoryCustom {
	
	void persistAccount(Account account) throws AccountException;

	List<AccountDTO> getAllAccountsByOwner(String owner);
	
	Account getAccountByAccountId(String accountId) throws AccountException;
	
	AccountDTO getAccountDTOByAccountId(String accountId) throws AccountException;
	
	void deleteAccountByAccountId(String accountId) throws AccountException;
}
