package ru.mamsta.refact;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class MyApp implements DataConnection {

	private static int COUNT_LOAD = 0;
	private static int COUNT_SAVE = 0;
	private static int startYear = 1990;
	private static int endYear = 2020;
	private String y;

	public MyApp(String y) {
		this.y = y;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("app v.1.13");

			for (int i = startYear; i < endYear; i++) {
				int sum = 0;
				// непонятно зачем счётчикам присваивается каждый раз 0,
				// особенно второму, тогда в файл всегда будет записываться 0?
				COUNT_LOAD = 0;
				COUNT_SAVE = 0;
				String y = String.valueOf(i);
				final DataConnection dataConnection = new DataConnectionFactory().getInstance(y);
				sum = dataConnection.loadDatas(sum);
				double qq = sum > 0 ? (double) sum / (double) COUNT_LOAD : 0;
				if (qq > 0) {
					System.out.println(i + " " + qq);
				}
				dataConnection.saveData(i, (int) qq);
			}

			System.out.println("finished");
		} catch (final Exception e) {
			System.out.println("error: " + e.getMessage());
		}
	}

	public int loadDatas(int sum) throws Exception {
		try (final InputStream is = new FileInputStream(new File("1.txt"));
				final BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(" ");
				if (data.length > 3 && data[2].contains(y)) {
					sum += Integer.parseInt(data[3]);
					COUNT_LOAD++;
				}
			}
		}

		return sum;
	}

	public void saveData(int year, int qq) throws IOException {
		try (final OutputStream os = new FileOutputStream(new File("statistika.txt"), true);) {
			String s = COUNT_SAVE + " " + year + " " + qq + "\n";
			os.write(s.getBytes());
			COUNT_SAVE++;
		}
	}
}
