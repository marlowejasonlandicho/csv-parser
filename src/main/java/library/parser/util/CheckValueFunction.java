package library.parser.util;

import java.util.function.Function;

/**
 * A Java Function that does that parses and retrieves the data per cell in the
 * file
 * 
 * @author marlowelandicho
 *
 */
public class CheckValueFunction implements Function<String, Object> {

	/**
	 * A String indicator constant as specified
	 */
	private static final String STRING_INDICATOR = "\"";

	@Override
	public Object apply(String columnValue) {

		// Checks if cell value are wrapped in " character and return the data as String
		if (columnValue.startsWith(STRING_INDICATOR) && columnValue.endsWith(STRING_INDICATOR)) {
			return columnValue.replace(STRING_INDICATOR, "");
		} else {
			// Try to parse data as an integer
			try {
				return Integer.parseInt(columnValue);
			} catch (NumberFormatException nfe) {
			}

			// Try to parse data as Long
			try {
				return Long.parseLong(columnValue);
			} catch (NumberFormatException nfe) {
			}
			
			//Try to parse data as Decimal value
			try {
				return Float.parseFloat(columnValue);
			} catch (NumberFormatException nfe) {
			}

			//Try to parse data as Double Decimal value
			try {
				return Double.parseDouble(columnValue);
			} catch (NumberFormatException nfe) {
			}
		}

		return columnValue;
	}

}
