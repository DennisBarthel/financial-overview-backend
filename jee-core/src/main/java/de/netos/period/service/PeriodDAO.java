package de.netos.period.service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import de.netos.account.entry.Account;
import de.netos.account.entry.Account_;
import de.netos.period.PeriodDTO;
import de.netos.period.entity.Period;
import de.netos.period.entity.Period_;
import de.netos.period.exception.PeriodException;
import de.netos.period.exception.PeriodException.ErrorMessage;
import de.netos.period.service.dto.MinMaxPeriod;

@ApplicationScoped
@Transactional
public class PeriodDAO {

	@PersistenceContext(unitName = "myapp")
	private EntityManager em;

	public void persistPeriod(Period period) throws PeriodException {
		try {
			if (period.getId() != null) {
				em.merge(period);
			} else {
				em.persist(period);
			}
		} catch (EntityExistsException e) {
			throw new PeriodException(ErrorMessage.PERIOD_EXISTS, "");
		}
	}

	public List<PeriodDTO> getPeriods(String accountId, Optional<YearMonth> from, Optional<YearMonth> to) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PeriodDTO> query = builder.createQuery(PeriodDTO.class);
		Root<Period> period = query.from(Period.class);

		Join<Period, Account> join = period.join(Period_.ACCOUNT);

		query.select(builder.construct(PeriodDTO.class, period.get(Period_.PERIOD_ID),
				period.get(Period_.YEAR_MONTH_OF_PERIOD), join.get(Account_.ACCOUNT_ID), period.get(Period_.DEBIT),
				period.get(Period_.DEPOSIT), period.get(Period_.INTEREST)))
				.where(builder.equal(join.get(Account_.ACCOUNT_ID), accountId));
		from.ifPresent(f -> query.where(builder.greaterThanOrEqualTo(period.get(Period_.YEAR_MONTH_OF_PERIOD), f)));
		to.ifPresent(t -> query.where(builder.lessThanOrEqualTo(period.get(Period_.YEAR_MONTH_OF_PERIOD), t)));
		query.orderBy(builder.asc(period.get(Period_.YEAR_MONTH_OF_PERIOD)));

		return em.createQuery(query).getResultList();
	}

	public Period getPeriodByPeriodId(String periodId) throws PeriodException {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Period> query = builder.createQuery(Period.class);
		Root<Period> period = query.from(Period.class);

		query.where(builder.equal(period.get(Period_.PERIOD_ID), periodId));

		try {
			return em.createQuery(query).getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			throw new PeriodException(ErrorMessage.PERIOD_NOT_FOUND, "Period not found");
		}
	}

	public MinMaxPeriod getMinMaxPeriod() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MinMaxPeriod> query = builder.createQuery(MinMaxPeriod.class);
		Root<Period> root = query.from(Period.class);
		query.select(builder.construct(MinMaxPeriod.class, builder.least(root.get(Period_.YEAR_MONTH_OF_PERIOD)),
				builder.greatest(root.get(Period_.YEAR_MONTH_OF_PERIOD))));

		return em.createQuery(query).getSingleResult();
	}

	public void deletePeriodById(String periodId) throws PeriodException {
		Period period = getPeriodByPeriodId(periodId);

		em.remove(period);
	}
}
