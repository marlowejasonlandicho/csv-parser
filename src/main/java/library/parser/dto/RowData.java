package library.parser.dto;

import java.util.HashMap;
import java.util.Map;

public class RowData {

	private Map<String, Object> values;

	public RowData() {
		values = new HashMap<>();
	}

	public Map<String, Object> getValues() {
		return values;
	}

	public void addValue(String key, Object value) {
		values.put(key, value);
	}

	@Override
	public String toString() {
		return "RowData [values=" + values.toString() + "]";
	}

}
