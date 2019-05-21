package de.netos.account;

public class CreateAccountDTO {

	private AccountType type;
	private String name;
	private String number;
	private Currency currency;
	private String owner;

	public CreateAccountDTO() {
	}

	public CreateAccountDTO(AccountType type, String name, String number, Currency currency, String owner) {
		this.type = type;
		this.name = name;
		this.number = number;
		this.currency = currency;
		this.owner = owner;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
