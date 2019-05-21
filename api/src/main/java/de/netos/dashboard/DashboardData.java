package de.netos.dashboard;

import java.util.ArrayList;
import java.util.List;

import de.netos.account.AccountDTO;

public class DashboardData {

	private AccountDTO accountDTO;
	private List<Dataset> datasets = new ArrayList<>();

	public DashboardData() {
	}

	public DashboardData(AccountDTO accountDTO, List<Dataset> datasets) {
		this.accountDTO = accountDTO;
		this.datasets = datasets;
	}

	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}

	public List<Dataset> getDatasets() {
		return datasets;
	}

	public void addDataset(Dataset dataset) {
		this.datasets.add(dataset);
	}
}
