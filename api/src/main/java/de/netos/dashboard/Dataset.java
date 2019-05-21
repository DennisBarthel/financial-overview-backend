package de.netos.dashboard;

import java.util.ArrayList;
import java.util.List;

public class Dataset {

	private String name;
	private List<Double> data = new ArrayList<>();

	public Dataset() {
	}

	public Dataset(String name, List<Double> data) {
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Double> getData() {
		return data;
	}

	public void addData(Double data) {
		this.data.add(data);
	}
}
