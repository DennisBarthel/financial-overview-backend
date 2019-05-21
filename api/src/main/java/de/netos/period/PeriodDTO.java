package de.netos.period;

import java.time.YearMonth;

public class PeriodDTO {

	private String periodId;
	private YearMonth yearMonthOfPeriod;
	private String accountId;
	private double debit;
	private double deposit;
	private double interest;

	public PeriodDTO() {
	}

	public PeriodDTO(String periodId, YearMonth yearMonthOfPeriod, String accountId, double debit, double deposit,
			double interest) {
		this.periodId = periodId;
		this.yearMonthOfPeriod = yearMonthOfPeriod;
		this.accountId = accountId;
		this.debit = debit;
		this.deposit = deposit;
		this.interest = interest;
	}

	public PeriodDTO(String periodId) {
		this.periodId = periodId;
	}

	public String getPeriodId() {
		return periodId;
	}

	public YearMonth getYearMonthOfPeriod() {
		return yearMonthOfPeriod;
	}

	public String getAccountId() {
		return accountId;
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
