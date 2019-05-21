package de.netos.account.service;

import java.util.List;
import java.util.function.Supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import de.netos.account.AccountDTO;
import de.netos.account.entry.Account;
import de.netos.account.entry.Account_;
import de.netos.account.exception.AccountException;
import de.netos.account.exception.AccountException.ErrorMessage;
import de.netos.period.entity.Period;
import de.netos.period.entity.Period_;

@ApplicationScoped
@Transactional
public class AccountDAO {

	@PersistenceContext(unitName = "myapp")
	private EntityManager em;

	public void persistAccount(Account account) throws AccountException {
		try {
			if (account.getId() != null) {
				em.merge(account);
			} else {
				em.persist(account);
			}
		} catch (EntityExistsException e) {
			throw new AccountException(ErrorMessage.ACCOUNT_EXISTS,
					String.format("An account with the number %s does already exist.", account.getNumber()));
		}
	}

	public List<AccountDTO> getAllAccounts() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<AccountDTO> criteriaQuery = criteriaBuilder.createQuery(AccountDTO.class);

		Root<Account> root = criteriaQuery.from(Account.class);
		criteriaQuery.select(criteriaBuilder.construct(AccountDTO.class, root.get(Account_.ACCOUNT_ID),
				root.get(Account_.ACCOUNT_TYPE), root.get(Account_.NUMBER), root.get(Account_.CURRENCY),
				root.get(Account_.OWNER), root.get(Account_.NAME)));

		TypedQuery<AccountDTO> query = em.createQuery(criteriaQuery);

		return query.getResultList();
	}

	public void deleteAccountById(String accountId) throws AccountException {
		Account account = findAccountByAccountId(accountId);

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaDelete<Period> criteriaDelete = criteriaBuilder.createCriteriaDelete(Period.class);
		Root<Period> root = criteriaDelete.from(Period.class);
		criteriaDelete.where(criteriaBuilder.equal(root.get(Period_.ACCOUNT), account));

		em.createQuery(criteriaDelete).executeUpdate();

		em.remove(account);
	}

	public AccountDTO getAccountDTOByAccountId(String accountId) throws AccountException {
		if (!isAccountIdValid(accountId)) {
			throw new AccountException(ErrorMessage.INVALID_ACCOUNTID,
					String.format("AccountId [%s] is invalid", accountId));
		}

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AccountDTO> query = builder.createQuery(AccountDTO.class);
		Root<Account> root = query.from(Account.class);

		query.select(builder.construct(AccountDTO.class, root.get(Account_.ACCOUNT_ID), root.get(Account_.ACCOUNT_TYPE),
				root.get(Account_.NUMBER), root.get(Account_.CURRENCY), root.get(Account_.OWNER),
				root.get(Account_.NAME))).where(builder.equal(root.get(Account_.ACCOUNT_ID), accountId));

		return executeSingleResultQuery(query, () -> String.format("No account found for %s.", accountId));
	}

	public Account findAccountByAccountId(String accountId) throws AccountException {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Account> query = builder.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);

		query.where(builder.equal(root.get(Account_.ACCOUNT_ID), accountId));

		return executeSingleResultQuery(query, () -> String.format("No account found for %s.", accountId));
	}

	private <T> T executeSingleResultQuery(CriteriaQuery<T> query, Supplier<String> errorMessageSupplier)
			throws AccountException {
		try {
			return em.createQuery(query).getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			throw new AccountException(ErrorMessage.ACCOUNT_NOT_FOUND, errorMessageSupplier.get());
		}
	}

	private boolean isAccountIdValid(String accountId) {
		return accountId != null;
	}
}
