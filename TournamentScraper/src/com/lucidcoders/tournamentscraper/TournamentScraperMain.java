package com.lucidcoders.tournamentscraper;

import java.io.IOException;
import java.net.URISyntaxException;

import com.lucidcoders.tournamentscraper.scrape.AtlasCasinoFullScrape;
import com.lucidcoders.tournamentscraper.scrape.AtlasDelete;
import com.lucidcoders.tournamentscraper.scrape.AtlasFullScrape;
import com.lucidcoders.tournamentscraper.scrape.AtlasQuery;
import com.lucidcoders.tournamentscraper.scrape.AtlasSeriesFullScrape;

public class TournamentScraperMain {

    public static void main(String[] args) throws URISyntaxException, IOException {
	
//	new AtlasFullScrape().execute();
//	new AtlasCasinoFullScrape().execute();
	new AtlasSeriesFullScrape().execute();
//	new AtlasQuery().execute();
//	new AtlasDelete().execute();
	
    }
}










































