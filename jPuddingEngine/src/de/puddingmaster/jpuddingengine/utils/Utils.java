package de.puddingmaster.jpuddingengine.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import de.fridtjof_de.puddingapi.logger;

public class Utils {

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			
			br.close();
		} catch (IOException e) {
			logger.ERROR("Failed to load file!");
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch(NumberFormatException e) {
			logger.WARN("The world is corrupted!");
			e.printStackTrace();
			return 1;
		}
	}
}
