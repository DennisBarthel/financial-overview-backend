package de.netos.dashboard;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DashboardDTO {

	private List<YearMonth> labels;
	private List<DashboardData> dashboardData = new ArrayList<>();

	public DashboardDTO() {
	}

	public DashboardDTO(List<YearMonth> labels, List<DashboardData> chartData) {
		this.labels = labels;
		this.dashboardData = chartData;
	}

	public List<DashboardData> getChartData() {
		return dashboardData;
	}

	public void addChartData(DashboardData chartData) {
		this.dashboardData.add(chartData);
	}

	public List<YearMonth> getLabels() {
		return labels;
	}

	public void setLabels(List<YearMonth> labels) {
		this.labels = labels;
	}
}
