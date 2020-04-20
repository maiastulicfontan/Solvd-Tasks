package com.solvd.files;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FileRunner {
	private static final Logger LOGGER = LogManager.getLogger(FileRunner.class);
	
	public static void main(String[] args) throws IOException {
		
		//
		try {
			File textFileA = new File("src/main/resources/textfileA.txt");
			/*File textFileB = new File("src/main/resources/textfileB.txt");
			File textFileC = new File ("src/main/resources/textfileC.txt");
			MyFileUtils.countWordOccurrences(textFileA);
			MyFileUtils.countWordsNoOverlap(textFileB);
			MyFileUtils.countWordsSimpleWay(textFileC);*/
			MyFileUtils.countWordsHashMap(textFileA);
			
		} catch (IOException e) {
			LOGGER.error("Can't find some of the files",e);
		}
		
	}
}
