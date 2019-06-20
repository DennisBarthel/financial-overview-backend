package de.netos.account;

public class AccountDTO {

	private String id;
	private AccountType type;
	private String number;
	private Currency currency;
	private String name;

	public AccountDTO() {
	}

	public AccountDTO(String id, AccountType type, String number, Currency currency, String name) {
		this.id = id;
		this.type = type;
		this.number = number;
		this.currency = currency;
		this.name = name;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
