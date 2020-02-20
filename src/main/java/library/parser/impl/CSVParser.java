package library.parser.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import library.parser.dto.RowData;
import library.parser.exception.InvalidDataException;
import library.parser.util.CheckValueFunction;
import library.parser.util.FileHandler;

public class CSVParser {

	private static final String SEMI_COLON = ";";
	private String delimiter;

	public CSVParser() {
		this.delimiter = SEMI_COLON;
	}

	public CSVParser(String delimiter) {
		this.delimiter = delimiter;
	}

	public RowData[] parse(String fileName) {

		final List<String> lineAsList = FileHandler.openFile(fileName);
		List<RowData> rowDataList = null;
		try {
			rowDataList = parseData(lineAsList);

		} catch (InvalidDataException e) {
			e.printStackTrace();
		}

		if (Objects.nonNull(rowDataList)) {
			return rowDataList.toArray(new RowData[0]);
		} else {
			return new RowData[0];
		}
	}

	public RowData[] parse(File file) {

		final List<String> lineAsList = FileHandler.openFile(file);
		List<RowData> rowDataList = null;
		try {
			rowDataList = parseData(lineAsList);
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}

		if (Objects.nonNull(rowDataList)) {
			return rowDataList.toArray(new RowData[0]);
		} else {
			return new RowData[0];
		}
	}

	public RowData[] parse(FileInputStream fileInputStream) {

		final List<String> lineAsList = FileHandler.openFile(fileInputStream);
		List<RowData> rowDataList = null;

		try {
			rowDataList = parseData(lineAsList);
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}

		if (Objects.nonNull(rowDataList)) {
			return rowDataList.toArray(new RowData[0]);
		} else {
			return new RowData[0];
		}

	}

	private List<RowData> parseData(final List<String> lineAsList) throws InvalidDataException {
		final List<RowData> rowDataList = new ArrayList<>();
		final List<String> headerNameList = new ArrayList<>();

		Stream.of(lineAsList.get(0).split(this.delimiter)).forEach(headerNameList::add);

		Function<String, Object> checkValue = new CheckValueFunction();

		for (int i = 1; i < lineAsList.size(); i++) {
			String lineToParse = lineAsList.get(i);
			List<Object> valueList = Stream.of(lineToParse.split(this.delimiter)).map(checkValue)
					.collect(Collectors.toList());

			if (valueList.size() < headerNameList.size()) {
				throw new InvalidDataException("Invalid data at line: " + i);
			}

			RowData rowData = new RowData();

			for (int j = 0; j < headerNameList.size(); j++) {
				Object cellValue = valueList.get(j);
				rowData.addValue(headerNameList.get(j), cellValue);
			}
			rowDataList.add(rowData);

		}
		return rowDataList;
	}

}
