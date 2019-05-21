package de.netos.period.service;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;

import de.netos.account.entry.Account;
import de.netos.account.exception.AccountException;
import de.netos.account.service.AccountDAO;
import de.netos.base.util.YearMonthFormatter;
import de.netos.period.CreatePeriodDTO;
import de.netos.period.ModifyPeriodDTO;
import de.netos.period.PeriodDTO;
import de.netos.period.entity.Period;
import de.netos.period.exception.PeriodException;
import de.netos.period.exception.PeriodException.ErrorMessage;

@ApplicationScoped
public class PeriodService {

	@Inject
	private AccountDAO accountDBService;

	@Inject
	private PeriodDAO periodDBService;

	@Inject
	@YearMonthFormatter
	private DateTimeFormatter formatter;

	public void addPeriod(CreatePeriodDTO createPeriod) throws AccountException, PeriodException {
		Account account = accountDBService.findAccountByAccountId(createPeriod.getAccountId());

		String transactionId = DigestUtils
				.md5Hex(createPeriod.getAccountId() + "_" + createPeriod.getMonth() + "_" + createPeriod.getYear());
		Period period = new Period(transactionId,
				YearMonth.of(createPeriod.getYear(), Month.valueOf(createPeriod.getMonth())));
		period.setDebit(createPeriod.getDebit());
		period.setDeposit(createPeriod.getDeposit());
		period.setInterest(createPeriod.getInterest());
		period.setAccount(account);

		periodDBService.persistPeriod(period);
	}

	public List<PeriodDTO> getPeriods(String accountId, String from, String to) throws PeriodException {
		Optional<YearMonth> fromYearMonth = parseToYearMonth(from);
		Optional<YearMonth> toYearMonth = parseToYearMonth(to);

		return periodDBService.getPeriods(accountId, fromYearMonth, toYearMonth);
	}

	public void modifyPeriod(String periodId, ModifyPeriodDTO modifyPeriod) throws PeriodException {
		Period period = periodDBService.getPeriodByPeriodId(periodId);

		period.setDebit(modifyPeriod.getDebit());
		period.setDeposit(modifyPeriod.getDeposit());
		period.setInterest(modifyPeriod.getInterest());

		periodDBService.persistPeriod(period);
	}

	public void deletePeriod(String periodId) throws PeriodException {
		periodDBService.deletePeriodById(periodId);
	}

	private Optional<YearMonth> parseToYearMonth(String input) throws PeriodException {
		if (input != null) {
			try {
				return Optional.ofNullable(YearMonth.parse(input, formatter));
			} catch (DateTimeParseException e) {
				throw new PeriodException(ErrorMessage.INVALID_TIME_PERIOD, String.format("Invalid input [%s]", input));
			}
		} else {
			return Optional.empty();
		}
	}
}
