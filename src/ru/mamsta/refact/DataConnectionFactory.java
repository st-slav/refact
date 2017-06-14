package ru.mamsta.refact;

public class DataConnectionFactory {
	
	public DataConnection getInstance(final String y) {
		return new MyApp(y);
	}
}
