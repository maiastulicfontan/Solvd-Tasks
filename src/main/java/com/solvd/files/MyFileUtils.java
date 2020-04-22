package com.solvd.files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MyFileUtils {
	
	private static final Logger LOGGER = LogManager.getLogger(MyFileUtils.class);
	
	public MyFileUtils() {}
	
	//this method replaces common separators with blank spaces, converts the string to uppercase and returns the array resulted from splitting the text using whitespace as separator 
	public static String[] cleanStringToArray(String string) {
		string = StringUtils.replaceEach(string, new String[] {".", "," , ":" , ";" , "(" , ")" , "-", "?"}, new String[]{" ", " " , " " , " " , " " , " " , " ", " "});
		string = StringUtils.upperCase(string);
		String [] tmpArray = StringUtils.split(string);
		return tmpArray;
	}
	

	public static void countWords (File textFile) throws IOException {
		LOGGER.info("Counting words in text file...");
		String string = FileUtils.readFileToString(textFile, "UTF-8");
		HashMap <String, Integer> tmpMap = new HashMap <String, Integer>();
		String[] list = MyFileUtils.cleanStringToArray(string);
		for(String word : list) {
			if (tmpMap.containsKey(word)) {
				tmpMap.put(word, tmpMap.get(word)+1);
			}
			tmpMap.putIfAbsent(word,1);	
		}
		FileUtils.writeStringToFile(textFile, "\n\nOCCURRENCES OF WORDS IN THE FILE\n","UTF-8", true);
		tmpMap.forEach((word, count)-> {
			try {
				FileUtils.writeStringToFile(textFile,"\n"+ word+": "+count, "UTF-8", true);
			} catch (IOException e) {
				LOGGER.error(e);
			}
		});
	}
}
