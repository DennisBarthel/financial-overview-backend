package de.netos.period.entity;

import java.time.YearMonth;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import de.netos.account.entity.Account;
import de.netos.base.YearMonthDateAttributeConverter;

@Entity
@Table(name = "Periods",
		uniqueConstraints = @UniqueConstraint(columnNames = { "periodId" }))
public class Period {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@NotBlank
	private String periodId;

	@Column(columnDefinition = "date")
	@Convert(converter = YearMonthDateAttributeConverter.class)
	private YearMonth yearMonthOfPeriod;

	@Column(nullable = true)
	private Double debit;

	@Column(nullable = true)
	private Double deposit;

	@Column(nullable = true)
	private Double interest;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;

	public Period() {
	}

	public Period(String periodId, YearMonth yearMonthOfPeriod) {
		this.periodId = periodId;
		this.yearMonthOfPeriod = yearMonthOfPeriod;
	}

	public Long getId() {
		return id;
	}

	public String getPeriodId() {
		return periodId;
	}

	public YearMonth getYearMonthOfPeriod() {
		return yearMonthOfPeriod;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Double getDebit() {
		return debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Period other = (Period) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
