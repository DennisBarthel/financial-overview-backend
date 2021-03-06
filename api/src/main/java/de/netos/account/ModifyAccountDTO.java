package de.netos.account;

public class ModifyAccountDTO {

	private AccountType type;
	private String name;
	private String number;
	private Currency currency;

	public ModifyAccountDTO() {
	}

	public ModifyAccountDTO(AccountDTO account) {
		this.type = account.getType();
		this.name = account.getName();
		this.number = account.getNumber();
		this.currency = account.getCurrency();
	}

	public ModifyAccountDTO(AccountType type, String name, String number, Currency currency) {
		this.type = type;
		this.name = name;
		this.number = number;
		this.currency = currency;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
