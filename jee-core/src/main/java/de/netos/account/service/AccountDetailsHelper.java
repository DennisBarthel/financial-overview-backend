package de.netos.account.service;

import java.time.Month;
import java.time.YearMonth;
import java.util.List;

import de.netos.period.PeriodDTO;

public class AccountDetailsHelper {

	public Result add(List<PeriodDTO> periods) {
		return periods.stream().collect(Result::new, (result, period) -> result.add(period), (r1, r2) -> r1.add(r2));
	}

	static class Result {
		private double deposit;
		private double debit;
		private double interest;
		private YearMonth lastPeriod = YearMonth.of(1970, Month.JANUARY);

		public double getDebit() {
			return debit;
		}

		public double getDeposit() {
			return deposit;
		}

		public double getInterest() {
			return interest;
		}

		public YearMonth getLastPeriod() {
			return lastPeriod;
		}

		private void add(PeriodDTO period) {
			deposit += period.getDeposit();
			debit += period.getDebit();
			interest += period.getInterest();
			if (period.getYearMonthOfPeriod().isAfter(this.lastPeriod)) {
				this.lastPeriod = period.getYearMonthOfPeriod();
			}
		}

		private void add(Result result) {
			this.deposit += result.deposit;
			this.debit += result.debit;
			this.interest += result.interest;
			if (result.lastPeriod.isAfter(this.lastPeriod)) {
				this.lastPeriod = result.lastPeriod;
			}
		}
	}
}
