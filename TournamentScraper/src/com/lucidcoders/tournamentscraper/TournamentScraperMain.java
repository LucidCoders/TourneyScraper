package com.lucidcoders.tournamentscraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.lucidcoders.tournamentscraper.scrape.AtlasCasinoFullScrape;
import com.lucidcoders.tournamentscraper.scrape.AtlasDelete;
import com.lucidcoders.tournamentscraper.scrape.AtlasDetailsScrape;
import com.lucidcoders.tournamentscraper.scrape.AtlasFullScrape;
import com.lucidcoders.tournamentscraper.scrape.AtlasQuery;
import com.lucidcoders.tournamentscraper.scrape.AtlasSeriesFullScrape;


@SuppressWarnings({"unused"})
public class TournamentScraperMain {

    public static void main(String[] args) throws URISyntaxException, IOException {
	
//	new AtlasFullScrape().execute();
//	new AtlasCasinoFullScrape().execute();
	new AtlasSeriesFullScrape().execute();
//	new AtlasQuery().execute();
//	new AtlasDelete().execute();
	
//	ArrayList<String> test = new ArrayList<String>();
//	test.add("http://www.pokeratlas.com/poker-tournament/palm-beach-kc-west-palm-beach-65-1215pm-nl-holdem-poker-tournament?topid=99461-2015-08-04");
//	new AtlasDetailsScrape(test).execute();;
	
    }
}










































