package com.csv.java.CSVFile.controller;

import com.csv.java.CSVFile.model.PayLoad;
import com.csv.java.CSVFile.service.ReadCsvService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/csv")
@CrossOrigin
public class ReadCsvController {
	@Autowired
	private ReadCsvService readCsvService;

	@PostMapping("/upload-csv-file/{month}/{year}")
	public ResponseEntity<List<String>> readCsvContent(@RequestBody List<String> lines, @PathVariable String month, @PathVariable String year) {

		//List<String> readAllContent = this.readCsvService.readAllContent(lines ,month, year);
		 List<String> readCsv = readCsvService.readCsvContent(month,year, lines);

		if (lines.size() == 0) {
			String string = "Record not found in csv file";
			return new ResponseEntity<List<String>>(Arrays.asList(string), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<String>>(readCsv, HttpStatus.OK);
		}
	}
	@GetMapping("/readcsv/{month}/{year}")
    public ResponseEntity<List<String>> uploadAndReadCSVFile(
    		@PathVariable String month,@PathVariable String year) {
		
			List<String> lines = new ArrayList<>();
		
		     List<String> readCsv = readCsvService.readCsvContent(month,year, lines);
		     
		if (readCsv.isEmpty()) {
			String string="file is empty";
			return new ResponseEntity<List<String>>(Arrays.asList(string), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<String>>(readCsv, HttpStatus.OK);
		}
	}
	
	
	
}