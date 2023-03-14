package com.csv.java.CSVFile.model;


import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PayLoad {
	private List<String> line;

	public List<String> getLine() {
		return line;
	}

	public void setLine(List<String> line) {
		this.line = line;
	}

	public PayLoad() {
		super();
		
	}

	public PayLoad(List<String> line) {
		super();
		this.line = line;
	}
	
}
