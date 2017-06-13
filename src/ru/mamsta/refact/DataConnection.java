package ru.mamsta.refact;

import java.io.IOException;

public interface DataConnection {
	int loadDatas(int sum) throws Exception; 
	void saveData(int year, int qq) throws IOException; 
}
