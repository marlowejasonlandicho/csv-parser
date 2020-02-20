package library.parser.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class FileHandler {

	private FileHandler() {
	}

	public static List<String> openFile(String fileName) {
		final List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(list::add);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;

	}

	public static List<String> openFile(File file) {
		final List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
			stream.forEach(list::add);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;

	}

	public static List<String> openFile(InputStream fileInputStream) {
		final List<String> list = new ArrayList<>();

		final BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
}
