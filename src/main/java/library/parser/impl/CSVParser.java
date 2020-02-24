package library.parser.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import library.parser.dto.RowData;
import library.parser.exception.InvalidDataException;
import library.parser.util.CheckValueFunction;
import library.parser.util.FileHandler;

/**
 * Main class for parsing csv.
 * 
 * @author marlowelandicho
 *
 */
public class CSVParser {

	/**
	 * Constant for the default delimiter
	 */
	private static final String SEMI_COLON = ";";

	/**
	 * The default delimiter
	 */
	private String delimiter;

	/**
	 * Constructor with default delimiter
	 */
	public CSVParser() {
		this.delimiter = SEMI_COLON;
	}

	/**
	 * Constructor for specifying a delimiter
	 * 
	 * @param delimiter the default delimiter
	 * 
	 */
	public CSVParser(String delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * 
	 * Parses a file given a filename. Must point to a valid path in the file system
	 * 
	 * @param fileName name of the file that is present in the file system
	 * 
	 * @return an array of RowData representing the data parsed
	 * 
	 * @throws InvalidDataException
	 * 
	 */
	public RowData[] parse(String fileName) throws InvalidDataException {

		final List<String> lineAsList = FileHandler.openFile(fileName);
		List<RowData> rowDataList = null;
		rowDataList = parseData(lineAsList);

		return rowDataList.toArray(new RowData[0]);
	}

	/**
	 * 
	 * Parses a file given an actual File. Must point to a valid file in the file
	 * system
	 * 
	 * @param file a file that is present in the file system
	 * 
	 * @return an array of RowData representing the data parsed
	 * 
	 * @throws InvalidDataException
	 * 
	 */
	public RowData[] parse(File file) throws InvalidDataException {

		final List<String> lineAsList = FileHandler.openFile(file);
		List<RowData> rowDataList = null;
		rowDataList = parseData(lineAsList);

		return rowDataList.toArray(new RowData[0]);
	}

	/**
	 * 
	 * Parses a file given a FileInputStream. Must point to a valid path in the file
	 * system
	 * 
	 * @param fileInputStream InputStram representing the file in the file system
	 * 
	 * @return an array of RowData representing the data parsed
	 * 
	 * @throws InvalidDataException
	 * 
	 */
	public RowData[] parse(FileInputStream fileInputStream) throws InvalidDataException {

		final List<String> lineAsList = FileHandler.openFile(fileInputStream);
		List<RowData> rowDataList = null;

		rowDataList = parseData(lineAsList);

		return rowDataList.toArray(new RowData[0]);

	}

	/**
	 * Internal function that parses the data per line
	 * 
	 * @param lineAsList a list of String where each entry represents a line from
	 *                   the file
	 * @return RowData as List
	 */
	private List<RowData> parseData(final List<String> lineAsList) {
		final List<RowData> rowDataList = new ArrayList<>();
		final List<String> headerNameList = new ArrayList<>();
		final Function<String, Object> checkValue = new CheckValueFunction();

		// Get the header portion (top-most row) of the file and stores the row data to
		// headerNameList
		Stream.of(lineAsList.get(0).split(this.delimiter)).forEach(headerNameList::add);

		// Loop thru the remaining lines
		for (int i = 1; i < lineAsList.size(); i++) {

			String lineToParse = lineAsList.get(i);
			// Parse the line by splitting the data by a delimiter
			// Pass the data to a Java Function that collects the correct data
			List<Object> valueList = Stream.of(lineToParse.split(this.delimiter)).map(checkValue)
					.collect(Collectors.toList());

			// Throw an exception if data is missing
			if (valueList.size() < headerNameList.size()) {
				throw new InvalidDataException("Invalid data at line: " + i);
			}

			// Store parsed data as RowData
			final RowData rowData = new RowData();

			for (int j = 0; j < headerNameList.size(); j++) {
				Object cellValue = valueList.get(j);
				rowData.addValue(headerNameList.get(j), cellValue);
			}

			rowDataList.add(rowData);
		}
		return rowDataList;
	}

}
