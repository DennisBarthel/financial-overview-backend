package de.netos.period;

public class ModifyPeriodDTO {

	private double debit;
	private double deposit;
	private double interest;

	public ModifyPeriodDTO() {
	}

	public ModifyPeriodDTO(double debit, double deposit, double interest) {
		this.debit = debit;
		this.deposit = deposit;
		this.interest = interest;
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
