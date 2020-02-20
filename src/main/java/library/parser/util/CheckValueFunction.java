package library.parser.util;

import java.util.function.Function;

public class CheckValueFunction implements Function<String, Object> {

	private static final String STRING_INDICATOR = "\"";

	@Override
	public Object apply(String columnValue) {
		
		if (columnValue.startsWith(STRING_INDICATOR) && columnValue.endsWith(STRING_INDICATOR)) {
			return columnValue.replace(STRING_INDICATOR, "");
		} else {
			try {
				return Integer.parseInt(columnValue);
			} catch (NumberFormatException nfe) {}
			
			try {
				return Long.parseLong(columnValue);
			} catch (NumberFormatException nfe) {}
			
			try {
				return Float.parseFloat(columnValue);
			} catch (NumberFormatException nfe) {}
			
			try {
				return Double.parseDouble(columnValue);
			} catch (NumberFormatException nfe) {}
		}
		
		return columnValue;
	}

}
