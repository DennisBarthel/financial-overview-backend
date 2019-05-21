package de.netos.account;

public class AccountDetailsDTO {

	private AccountDTO account;
	private double totalDebit;
	private double totalDeposit;
	private double totalInterest;
	private double totalBalance;

	public AccountDetailsDTO() {
	}

	public AccountDetailsDTO(AccountDTO account, double totalDebit, double totalDeposit, double totalInterest,
			double totalBalance) {
		this.account = account;
		this.totalDebit = totalDebit;
		this.totalDeposit = totalDeposit;
		this.totalInterest = totalInterest;
		this.totalBalance = totalBalance;
	}

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	public double getTotalDebit() {
		return totalDebit;
	}

	public void setTotalDebit(double totalDebit) {
		this.totalDebit = totalDebit;
	}

	public double getTotalDeposit() {
		return totalDeposit;
	}

	public void setTotalDeposit(double totalDeposit) {
		this.totalDeposit = totalDeposit;
	}

	public double getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(double totalInterest) {
		this.totalInterest = totalInterest;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}
}
