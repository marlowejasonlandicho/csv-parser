package library.parser.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import library.parser.dto.RowData;
import library.parser.exception.InvalidDataException;

public class CSVParserTest {

	@Test
	public void testParseAsFilename() {
		CSVParser csvParser = new CSVParser();

		RowData[] rowData = csvParser.parse("src/test/resources/data.csv");
		for (RowData rowData2 : rowData) {
			System.out.println(rowData2);
		}
		assertNotNull(rowData);
		assertEquals(5, rowData.length);
	}

	@Test
	public void testParseAsFile() {
		CSVParser csvParser = new CSVParser();
		File file = new File("src/test/resources/data.csv");

		RowData[] rowData = csvParser.parse(file);
		assertNotNull(rowData);
		assertEquals(5, rowData.length);
	}

	@Test
	public void testParseAsFileInputStream() {
		CSVParser csvParser = new CSVParser();
		File file = new File("src/test/resources/data.csv");

		RowData[] rowData;
		try {
			rowData = csvParser.parse(new FileInputStream(file));
			assertNotNull(rowData);
			assertEquals(5, rowData.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	@Test(expected = InvalidDataException.class)
	public void testParseWithErrors() {
		CSVParser csvParser = new CSVParser();

		RowData[] rowData = csvParser.parse("src/test/resources/data_with_errors.csv");
 
//		assertNull(rowData);
	}

}
