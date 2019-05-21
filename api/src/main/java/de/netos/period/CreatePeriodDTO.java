package de.netos.period;

public class CreatePeriodDTO {

	private String month;
	private int year;
	private String accountId;
	private double debit;
	private double deposit;
	private double interest;

	public CreatePeriodDTO() {
	}

	public CreatePeriodDTO(String month, int year, String accountId, double debit, double deposit, double interest) {
		this.month = month;
		this.year = year;
		this.accountId = accountId;
		this.debit = debit;
		this.deposit = deposit;
		this.interest = interest;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}
}
