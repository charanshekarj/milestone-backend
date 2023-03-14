package com.csv.java.CSVFile.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.csv.java.CSVFile.model.PayLoad;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class ReadCsvServiceImpl implements ReadCsvService {

	public static ArrayList<String> filterData = null;
	public static ArrayList<String> filterData1 = null;

	private void readCsvFile(String file, String month, String year, List<String> lines) {

		try {
			CSVReader reader;
			if (lines.size() == 0) {
				reader = new CSVReader(new FileReader(file));
			}
			else {
				String csvString = lines.stream().collect(Collectors.joining(""));

		        // Convert the csvString to a Reader object
		        Reader csvReader = new StringReader(csvString);

		        // Use the CsvReader to read the CSV data
		        reader = new CSVReader(csvReader);
			}
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {

				for (int i = 0; i < nextLine.length; i++) {
					String dMonth = null;
					String dYear = null;
					String[] duedate = nextLine[i].split(",", 4);
					

					for (int j = 0; j < duedate.length; j++) {
						if (i > 3) {

							if (!duedate[j].equals("Due Date")) {
								if (duedate[j].contains("/")) {
									String str[] = duedate[j].split("/");
									dMonth=str[0];
									//dMonth = str[0].length() > 2 ? str[2].substring(2, 4) : str[2];
									dYear = str[2].length() > 2 ? str[2].substring(2, 4) : str[2];
									month = month.length() == 1 ? "0".concat(month) : month;
									getData(dMonth, dYear, month, year, nextLine);
                  

								} else if(duedate[j].contains("-")) {
									
									String str[] = duedate[j].split("-");
									dMonth=str[0];
								//dMonth = str[0].length() > 2 ? str[2].substring(2, 4) : str[2];
									dYear = str[2].length() > 2 ? str[2].substring(2, 4) : str[2];
									month = month.length() == 1 ? "0".concat(month) : month;
									
									getData(dMonth, dYear, month, year, nextLine);
								}
								else if(duedate[j].contains("-")) {
									
									String str[] = duedate[j].split("-");
									dMonth=str[0];
									if (str[0]=="Jan" || str[0]=="Feb" || str[0]=="Nov" ||str[0]=="Dec" ) {
										
									}
								//dMonth = str[0].length() > 2 ? str[2].substring(2, 4) : str[2];
									dYear = str[2].length() > 2 ? str[2].substring(2, 4) : str[2];
									month = month.length() == 1 ? "0".concat(month) : month;
									
									getData(dMonth, dYear, month, year, nextLine);
								}
							}

						}

					}

				}

			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (CsvValidationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void getData(String dMonth, String dYear, String month, String year, String[] value) {

		if (dYear.equals(year) && dMonth.equals(month)) {

			filterData.add(Arrays.toString(value));

		}
	}

		private void getData1(String dMonth, String dYear, String month, String year, String[] value) {
			

			if (dYear.equals(year) && dMonth.equals(month)) {


				filterData1.add(Arrays.toString(value));
			}
		}
//
	@Override
	public List<String> readCsvContent(String month, String year, List<String> lines) {
		
		filterData=new ArrayList<>();
		readCsvFile("./doc/resources/Asurint-SOW-wise-Milestones.csv", month, year, lines);

			
		
		return filterData;
	}

	@Override
	public List<String> readAllContent(PayLoad lines, String month, String year) {
		filterData1=new ArrayList<>();
		readAll(lines, month, year);
	
		return filterData1;
	}

	private void readAll(PayLoad lines, String month, String year) {

		@SuppressWarnings("unchecked")
		String[] nextLine =  (String[]) ((Collection<String>) lines).stream().toArray();
		while (nextLine != null) {

			for (int i = 0; i < nextLine.length; i++) {
				String dMonth = null;
				String dYear = null;
				String[] duedate = nextLine[i].split(",", 4);

				for (int j = 0; j < duedate.length; j++) {
					if (i > 3) {

						if (!duedate[j].equals("Due Date")) {
							if (duedate[j].contains("/")) {
								String str[] = duedate[j].split("/");
								dMonth = str[0];
								// dMonth = str[0].length() > 2 ? str[2].substring(2, 4) : str[2];
								dYear = str[2].length() > 2 ? str[2].substring(2, 4) : str[2];
								month = month.length() == 1 ? "0".concat(month) : month;
								getData1(dMonth, dYear, month, year, nextLine);

							} else if (duedate[j].contains("-")) {

								String str[] = duedate[j].split("-");
								dMonth = str[0];
								// dMonth = str[0].length() > 2 ? str[2].substring(2, 4) : str[2];
								dYear = str[2].length() > 2 ? str[2].substring(2, 4) : str[2];
								month = month.length() == 1 ? "0".concat(month) : month;

								getData1(dMonth, dYear, month, year, nextLine);
							} else if (duedate[j].contains("-")) {

								String str[] = duedate[j].split("-");
								dMonth = str[0];
								dYear = str[2].length() > 2 ? str[2].substring(2, 4) : str[2];
								month = month.length() == 1 ? "0".concat(month) : month;

								getData(dMonth, dYear, month, year, nextLine);
							}
						}
					}

				}
			}
		}
	}
}
