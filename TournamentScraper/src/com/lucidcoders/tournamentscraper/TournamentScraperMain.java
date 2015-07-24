package com.lucidcoders.tournamentscraper;

import java.io.IOException;
import java.net.URISyntaxException;

import com.lucidcoders.tournamentscraper.scrape.AtlasDetailsScrape;


public class TournamentScraperMain {

    public static void main(String[] args) throws URISyntaxException, IOException {
//	new MonthlySeriesScrape().execute();
//	new HardRockHollywoodScrape().execute();
//	new PokerAtlasScrape().execute();
//	new AtlasAreasScrape().execute();
	new AtlasDetailsScrape().execute();
//	new AtlasUpcomingScrape().execute();
    }
}
