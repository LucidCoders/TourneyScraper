package com.lucidcoders.tournamentscraper.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MyLogger {

    private BufferedWriter mBufferedWriter;
    
    public abstract boolean initialize();
    
    protected boolean initializeHelper(String fileName) {
	try {
	    String month = new SimpleDateFormat("yyyy_MM").format(new Date());
	    String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	    
//	    String directory = "C:/Users/Queezy/Dev/ScrapeLog/PokerAtlas/" + month + "/Archive4";
	    String directory = "C:/Users/Queezy/Dev/ScrapeLog/PokerAtlas/" + month;

	    File dir = new File(directory);
	    if (!dir.exists()) {
		dir.mkdirs();
	    }

	    File file = new File(directory + "/" + fileName + "_" + date + ".txt");
	    if (!file.exists()) {
		file.createNewFile();
	    }

	    FileWriter fw = new FileWriter(file.getAbsoluteFile());
	    mBufferedWriter = new BufferedWriter(fw);
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	return mBufferedWriter != null;
    }

    /**
     * Overwrites the log file
     * @param content
     */
    public void writeToLog(String content) {
	if (mBufferedWriter != null) {
	    try {
		mBufferedWriter.write(content);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    public void appendToLog(String content) {
	if (mBufferedWriter != null) {
	    try {
		mBufferedWriter.append("\n" + content);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    public void appendLogEntry(String content) {
	if (mBufferedWriter != null) {
	    try {
		mBufferedWriter.append("\n" + Util.getLogTimeStamp() + content);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
    
    /**
     * 
     * @param content
     * @param skipLine 
     * 		Skips line above and below.
     */
    public void appendLogEntry(String content, boolean skipLine) {
	if (mBufferedWriter != null) {
	    try {
		if (skipLine) mBufferedWriter.append("\n");
		appendLogEntry(content + "\n");
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    public void closeFile() {
	if (mBufferedWriter != null) {
	    try {
		mBufferedWriter.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}
