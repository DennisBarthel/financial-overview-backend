package de.netos.account;

import java.time.YearMonth;

public class AccountOverviewDTO {

	private AccountDTO account;
	private double totalBalance;
	private YearMonth lastPeriod;

	public AccountOverviewDTO() {
	}

	public AccountOverviewDTO(AccountDTO account, double totalBalance, YearMonth lastPeriod) {
		this.account = account;
		this.totalBalance = totalBalance;
		this.lastPeriod = lastPeriod;
	}

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public YearMonth getLastPeriod() {
		return lastPeriod;
	}

	public void setLastPeriod(YearMonth lastPeriod) {
		this.lastPeriod = lastPeriod;
	}
}
