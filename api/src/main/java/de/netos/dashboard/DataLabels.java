package de.netos.dashboard;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DataLabels {

	private List<YearMonth> labels = new ArrayList<>();

	public DataLabels() {
	}

	public DataLabels(List<YearMonth> labels) {
		super();
		this.labels = labels;
	}

	public List<YearMonth> getLabels() {
		return labels;
	}

	public void addLabel(YearMonth label) {
		this.labels.add(label);
	}
}
