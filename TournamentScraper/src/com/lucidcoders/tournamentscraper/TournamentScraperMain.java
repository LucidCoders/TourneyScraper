package com.lucidcoders.tournamentscraper;

import java.io.IOException;
import java.net.URISyntaxException;

import com.lucidcoders.tournamentscraper.scrape.AtlasFullScrape;
import com.lucidcoders.tournamentscraper.scrape.AtlasQuery;

public class TournamentScraperMain {

    public static void main(String[] args) throws URISyntaxException, IOException {
	
//	new AtlasFullScrape().execute();
	new AtlasQuery().execute();
	
    }
}










































