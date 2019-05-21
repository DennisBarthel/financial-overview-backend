package de.netos.account.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.netos.account.AccountDTO;
import de.netos.account.AccountDetailsDTO;
import de.netos.account.AccountOverviewDTO;
import de.netos.account.CreateAccountDTO;
import de.netos.account.ModifyAccountDTO;
import de.netos.account.entry.Account;
import de.netos.account.exception.AccountException;
import de.netos.account.service.AccountDetailsHelper.Result;
import de.netos.period.PeriodDTO;
import de.netos.period.service.PeriodDAO;

@ApplicationScoped
public class AccountService {

	@Inject
	private AccountDAO accountDAO;

	@Inject
	private PeriodDAO periodDAO;

	@Inject
	private AccountMapper mapper;

	public void createAccount(CreateAccountDTO accountJson) throws AccountException {
		Account account = mapper.mapJSONToEntity(accountJson);
		accountDAO.persistAccount(account);
	}

	public List<AccountOverviewDTO> getAccounts() {
		List<AccountOverviewDTO> resultList = new ArrayList<>();

		List<AccountDTO> allAccounts = accountDAO.getAllAccounts();
		for (AccountDTO accountDTO : allAccounts) {
			List<PeriodDTO> periods = periodDAO.getPeriods(accountDTO.getId(), Optional.empty(), Optional.empty());
			Result result = new AccountDetailsHelper().add(periods);

			resultList.add(new AccountOverviewDTO(accountDTO,
					result.getDeposit() + result.getInterest() - result.getDebit(), result.getLastPeriod()));
		}
		return resultList;
	}

	public void deleteAccount(String accountId) throws AccountException {
		accountDAO.deleteAccountById(accountId);
	}

	public AccountDetailsDTO getAccountByAccountId(String accountId) throws AccountException {
		AccountDetailsDTO detailsJson = new AccountDetailsDTO();

		AccountDTO account = accountDAO.getAccountDTOByAccountId(accountId);
		detailsJson.setAccount(account);

		List<PeriodDTO> periods = periodDAO.getPeriods(accountId, Optional.empty(), Optional.empty());
		Result result = new AccountDetailsHelper().add(periods);
		detailsJson.setTotalDebit(result.getDebit());
		detailsJson.setTotalDeposit(result.getDeposit());
		detailsJson.setTotalInterest(result.getInterest());
		detailsJson.setTotalBalance(result.getDeposit() + result.getInterest() - result.getDebit());

		return detailsJson;
	}

	public void modifyAccount(String accountId, ModifyAccountDTO modifyAccount) throws AccountException {
		Account account = accountDAO.findAccountByAccountId(accountId);
		mapper.mapJSONToEntity(account, modifyAccount);

		accountDAO.persistAccount(account);
	}
}
