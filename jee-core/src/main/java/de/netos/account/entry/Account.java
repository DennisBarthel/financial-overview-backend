package de.netos.account.entry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.netos.account.AccountType;
import de.netos.account.Currency;
import de.netos.period.entity.Period;

@Entity
@Table(indexes = { @Index(columnList = "accountId", unique = true) })
@NamedQueries(value = { @NamedQuery(name = Account.ALL_ACCOUNTS, query = "FROM Account"),
		@NamedQuery(name = Account.DELETE_ACCOUNT_BY_ACCOUNTID, query = "DELETE Account where accountId = :"
				+ Account.ACCOUNT_ID),
		@NamedQuery(name = Account.GET_ACCOUNT_BY_ACCOUNTID, query = "FROM Account where accountId = :"
				+ Account.ACCOUNT_ID) })
public class Account {

	public static final String ALL_ACCOUNTS = "allAccounts";
	public static final String DELETE_ACCOUNT_BY_ACCOUNTID = "deleteAccountByAccountId";
	public static final String GET_ACCOUNT_BY_ACCOUNTID = "getAccountByAccountId";

	public static final String ACCOUNT_ID = "accountId";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String accountId;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	private String name;

	private String number;

	@Enumerated(EnumType.STRING)
	private Currency currency;

	private String owner;

	@OneToMany(mappedBy = "account")
	private List<Period> periods = new ArrayList<>();

	public Account() {
	}

	public Account(AccountType accountType, String name, String number, String accountId, Currency currency,
			String owner) {
		this.accountType = accountType;
		this.name = name;
		this.number = number;
		this.accountId = accountId;
		this.currency = currency;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Period> getPeriods() {
		return periods;
	}

	public void addOverview(Period period) {
		periods.add(period);
		period.setAccount(this);
	}

	public void removeOverview(Period period) {
		periods.remove(period);
		period.setAccount(null);
	}
}
