package de.netos.period.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.netos.period.PeriodDTO;
import de.netos.period.exception.PeriodException;
import de.netos.period.service.PeriodService;

@RestController
@RequestMapping(path = "/v1/period")
public class PeriodController {
	
	@Autowired
	private PeriodService periodService;

	@GetMapping("/{accountId}")
	public List<PeriodDTO> getPeriods(
			@PathVariable("accountId") String accountId,
			@RequestParam(required = false, name = "fromDate") String fromDate,
			@RequestParam(required = false, name = "toDate") String toDate) throws PeriodException {
		return periodService.getPeriods(accountId, fromDate, toDate);
	}
}
