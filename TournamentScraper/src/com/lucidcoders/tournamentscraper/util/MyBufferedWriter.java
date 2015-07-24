package com.lucidcoders.tournamentscraper.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBufferedWriter {

    private BufferedWriter mBufferedWriter;

    public MyBufferedWriter() {
	try {
	    String date = new SimpleDateFormat("MM_dd_yyyy").format(new Date());

	    File dir = new File("C:/Users/Queezy/Dev/PokerAtlas/Crawler");
	    if (!dir.exists()) {
		dir.mkdirs();
	    }

	    File file = new File("C:/Users/Queezy/Dev/PokerAtlas/Crawler/" + date + ".txt");
	    if (!file.exists()) {
		file.createNewFile();
	    }

	    FileWriter fw = new FileWriter(file.getAbsoluteFile());
	    mBufferedWriter = new BufferedWriter(fw);

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /*
     * Overwrites the file
     */
    public void writeToFile(String content) {
	try {
	    mBufferedWriter.write(content);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void appendToFile(String content) {
	try {
	    mBufferedWriter.append("\n" + content);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void closeFile() {
	try {
	    mBufferedWriter.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
