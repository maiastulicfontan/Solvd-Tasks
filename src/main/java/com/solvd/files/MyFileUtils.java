package com.solvd.files;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MyFileUtils {
	
	private static final Logger LOGGER = LogManager.getLogger(MyFileUtils.class);
	private static final String[] separators = {".", "," , ":" , ";" , "(" , ")" , "-"};
	
	public MyFileUtils() {}
	
	//this method replaces common separators with blank spaces, converts the string to uppercase and returns the array resulted from splitting the text using whitespace as separator 
	public static String[] cleanStringToArray(String string) {
		for (String separator : separators) {
			string = string.replace(separator, " ");
		}
		string = StringUtils.upperCase(string);
		String [] tmpArray = StringUtils.split(string);
		return tmpArray;
	}
	
	/*this method allows string overlap. for example, if "the" counts as a word and there are other words containing "the", such as "there" or "they", this method
	will count those strings as matches for the string "the" */
	public static void countWordOccurrences(File textFile) throws IOException{
		LOGGER.info("Counting word occurrences in file allowing string overlap: "+textFile.getName());
		String string = FileUtils.readFileToString(textFile, "UTF-8");
		String[] list = MyFileUtils.cleanStringToArray(string);
		Set <String> set = new HashSet <String>(Arrays.asList(list));
		FileUtils.writeStringToFile(textFile, "\nOCCURRENCES OF WORDS IN THE FILE\n","UTF-8", true);
		for (String word : set) {
			FileUtils.writeStringToFile(textFile,"\n"+ word+": "+StringUtils.countMatches(string, word), "UTF-8", true);
		}
	}
	
	//this method doesn't allow string overlap
	public static void countWordsNoOverlap(File textFile) throws IOException {
		LOGGER.info("Counting word occurrences in file without allowing string overlap: "+textFile.getName());
		String string = FileUtils.readFileToString(textFile, "UTF-8");
		String[] list = MyFileUtils.cleanStringToArray(string);
		Set <String> set = new HashSet <String>(Arrays.asList(list));
		FileUtils.writeStringToFile(textFile, "\n\nOCCURRENCES OF WORDS IN THE FILE\n","UTF-8", true);
		for (String notDuplicatedWord : set) {
			int count = 0;
			for (String word : list ) {
				if (StringUtils.equals(notDuplicatedWord, word)) { 
					count++;
				}
			}
			FileUtils.writeStringToFile(textFile, "\n"+ notDuplicatedWord+ ":" + count, "UTF-8", true);
		}
	}
	
	/*this method, while a little bit more simple, will count as separate words those which are followed by an empty space and those followed by a punctuation mark.
	for example, according to this, "you" and "you." are two different words. it also allows string overlap */
	public static void countWordsSimpleWay (File textFile) throws IOException{
		String string = FileUtils.readFileToString(textFile, "UTF-8");
		String[] list = MyFileUtils.cleanStringToArray(string);
		Set <String> set = new HashSet <String>(Arrays.asList(list));
		for (String word : set) {
			FileUtils.writeStringToFile(textFile,"\n"+ word+": "+StringUtils.countMatches(string, word), "UTF-8", true);
		}
	}
	
	public static void countWordsHashMap (File textFile) throws IOException {
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
