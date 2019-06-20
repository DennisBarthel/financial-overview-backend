package de.netos.account.service;

import java.util.List;
import java.util.function.Supplier;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.netos.account.AccountDTO;
import de.netos.account.entity.Account;
import de.netos.account.entity.Account_;
import de.netos.account.exception.AccountException;
import de.netos.account.exception.AccountException.ErrorMessage;
import de.netos.period.entity.Period;
import de.netos.period.entity.Period_;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepositoryCustom {

	private EntityManager em;

	@Autowired
	public AccountRepositoryImpl(EntityManager entityManager) {
		this.em = entityManager;
	}
	
	@Override
	public void persistAccount(Account account) throws AccountException {
		try {
			if (account.getId() != null) {
				em.merge(account);
			} else {
				em.persist(account);
			}
		} catch (EntityExistsException exception) {
			throw new AccountException(ErrorMessage.ACCOUNT_EXISTS,
					String.format("An account with the number %s does already exist.", account.getNumber()),
					exception);
		}
	}

	public List<AccountDTO> getAllAccounts() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AccountDTO> query = builder.createQuery(AccountDTO.class);

		Root<Account> root = query.from(Account.class);
		query.select(builder.construct(AccountDTO.class, root.get(Account_.ACCOUNT_ID),
				root.get(Account_.ACCOUNT_TYPE), root.get(Account_.NUMBER), root.get(Account_.CURRENCY),
				root.get(Account_.NAME)));
		
		return em.createQuery(query).getResultList();
	}

	@Override
	public Account getAccountByAccountId(String accountId) throws AccountException {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Account> query = builder.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		
		query.where(builder.equal(root.get(Account_.ACCOUNT_ID), accountId));
		
		return executeSingleResultQuery(query, () -> String.format("No account found for %s", accountId));
	}
	
	@Override
	public AccountDTO getAccountDTOByAccountId(String accountId) throws AccountException {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<AccountDTO> query = builder.createQuery(AccountDTO.class);

		Root<Account> root = query.from(Account.class);
		query.select(builder.construct(AccountDTO.class, root.get(Account_.ACCOUNT_ID),
				root.get(Account_.ACCOUNT_TYPE), root.get(Account_.NUMBER), root.get(Account_.CURRENCY),
				root.get(Account_.NAME)));
		
		query.where(builder.equal(root.get(Account_.ACCOUNT_ID), accountId));
		
		return executeSingleResultQuery(query, () -> String.format("No account found for %s", accountId));
	}
	
	@Override
	public void deleteAccountByAccountId(String accountId) throws AccountException {
		Account account = getAccountByAccountId(accountId);
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaDelete<Period> deleteQuery = builder.createCriteriaDelete(Period.class);
		Root<Period> root = deleteQuery.from(Period.class);
		deleteQuery.where(builder.equal(root.get(Period_.ACCOUNT), account));
		
		em.createQuery(deleteQuery).executeUpdate();
		
		em.remove(account);
	}
	
	private <T> T executeSingleResultQuery(CriteriaQuery<T> query, Supplier<String> errorMessageSupplier) throws AccountException {
		try {
			return em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			throw new AccountException(ErrorMessage.ACCOUNT_NOT_FOUND, errorMessageSupplier.get(), e);
		}
	}
}
