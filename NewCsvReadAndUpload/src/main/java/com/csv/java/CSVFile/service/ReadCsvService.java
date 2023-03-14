package com.csv.java.CSVFile.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.csv.java.CSVFile.model.PayLoad;

public interface ReadCsvService {

	public List<String> readCsvContent(String month,String year,List<String> lines);

	//public List<String> readCsv(MultipartFile file, String month, String year);
	
	public List<String> readAllContent(PayLoad lines ,String month,String year);
}
