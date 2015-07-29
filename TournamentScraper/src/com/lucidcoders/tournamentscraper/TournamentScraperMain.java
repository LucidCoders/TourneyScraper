package com.lucidcoders.tournamentscraper;

import java.io.IOException;
import java.net.URISyntaxException;

import com.lucidcoders.tournamentscraper.scrape.AtlasFullScrape;
import com.lucidcoders.tournamentscraper.util.MyLogger;


public class TournamentScraperMain {

    public static void main(String[] args) throws URISyntaxException, IOException {
	
	MyLogger logger = MyLogger.getInstance();
	if (!logger.initialize()) return;
	logger.writeToLog("**************************************** ATLAS FULL SCRAPE LOG ****************************************");
	logger.appendToLog("*******************************************************************************************************\n");
	
	new AtlasFullScrape().execute();
	
	logger.closeFile();
    }
}










































