package com.lucidcoders.tournamentscraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.lucidcoders.tournamentscraper.gae.TourneyDetailService;
import com.lucidcoders.tournamentscraper.rest.response.AtlasSeriesResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasSeriesResponse.SeriesResult;
import com.lucidcoders.tournamentscraper.scrape.AtlasCasinoFullScrape;
import com.lucidcoders.tournamentscraper.scrape.AtlasCasinoFullScrapeMultiThread;
import com.lucidcoders.tournamentscraper.scrape.AtlasDelete;
import com.lucidcoders.tournamentscraper.scrape.AtlasDetailsScrape;
import com.lucidcoders.tournamentscraper.scrape.AtlasFullScrape;
import com.lucidcoders.tournamentscraper.scrape.AtlasQuery;
import com.lucidcoders.tournamentscraper.scrape.AtlasSeriesFullScrape;
import com.lucidcoders.tournamentscraper.scrape.AtlasSeriesFullScrapeMultiThread;
import com.lucidcoders.tournamentscraper.scrape.AtlasUpcomingScrape;
import com.lucidcoders.tournamentscraper.scrape.SeriesEventScrape;
import com.lucidcoders.tournamentscraper.util.MyGeocoder;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;
import com.lucidcoders.tourneyspot.backend.tourneyDetailApi.model.TourneyDetails;


@SuppressWarnings({"unused"})
public class TournamentScraperMain {

    public static void main(String[] args) throws URISyntaxException, IOException {
	
//	new AtlasSeriesFullScrape().execute();
//	new AtlasSeriesFullScrapeMultiThread().execute();
//	new AtlasQuery().execute();
//	new AtlasDelete().execute();
//	new AtlasCasinoFullScrapeMultiThread().execute();
	
//	ArrayList<String> test = new ArrayList<String>();
//	test.add("http://www.pokeratlas.com/poker-tournament/palm-beach-kc-west-palm-beach-65-1215pm-nl-holdem-poker-tournament?topid=99461-2015-08-04");
//	new AtlasDetailsScrape(test).execute();;
	
//	List<SeriesResult> mSeriesResults =  new ArrayList<>();
//	AtlasSeriesResponse response = new AtlasSeriesResponse();
//	SeriesResult result = response.new SeriesResult();
//	
//	result.setSeriesLink("http://www.pokeratlas.com/poker-tournament-series/wpt-choctaw-festival-of-poker-choctaw-durant-2015");
//	mSeriesResults.add(result);
//	new SeriesEventScrape(mSeriesResults).execute();
	
	// For testing upcoming Events
	String url  = "http://www.pokeratlas.com/las-vegas-nevada";
	ScrapeLogger eventLogger = new ScrapeLogger("EventDetailsScrape");
	eventLogger.initialize();
	eventLogger.writeToLog(
		"**************************************** ATLAS EVENT SCRAPE LOG ****************************************");
	eventLogger.appendToLog(
		"*********************************************************************************************************\n");
	AtlasUpcomingScrape upcomingScrape = new AtlasUpcomingScrape(url, 10, eventLogger);
	upcomingScrape.execute();

	List<String> eventLinks = upcomingScrape.getEventLinks();
	if (eventLinks.size() > 0) {

	    eventLogger.appendLogEntry("********** Success getting Event Links : " + url + " **********\n");

	    AtlasDetailsScrape detailScrape = new AtlasDetailsScrape(eventLinks).execute();

	    final List<TourneyDetails> eventDetails = detailScrape.getEventDetails();
	    if (eventDetails.size() > 0) {

		eventLogger.appendLogEntry(
			"********** Success getting Event Details : " + url + " **********\n");

		for (TourneyDetails tourneyDetails : eventDetails) {
		    try {
			TourneyDetailService.getInstance().updateEvent(tourneyDetails);
		    } catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		    }
		}
	    } else {
		eventLogger.appendLogEntry(
			"********** Failed to get Event Details : " + url + " **********\n");
	    }

	    List<String> failedEventUrls = detailScrape.getFailedUrls();
	    if (failedEventUrls.size() > 0) {
		eventLogger.appendLogEntry("********** Begin Failed Events Retry **********");

		AtlasDetailsScrape detailScrapeRetry = new AtlasDetailsScrape(failedEventUrls);
		detailScrapeRetry.execute();

		final List<TourneyDetails> eventDetailsRetry = detailScrapeRetry.getEventDetails();
		if (eventDetailsRetry.size() > 0) {

		    eventLogger.appendLogEntry(
			    "********** Success getting Event Details on Retry **********\n");

		    for (TourneyDetails tourneyDetails : eventDetailsRetry) {
			try {
			    TourneyDetailService.getInstance().updateEvent(tourneyDetails);
			} catch (IOException | GeneralSecurityException e) {
			    e.printStackTrace();
			}
		    }
		} else {
		    eventLogger.appendLogEntry(
			    "********** Failed to get Event Details on Retry **********\n");
		}
	    }

	} else {
	    eventLogger.appendLogEntry("********** Failed to get Event Links : " + url + " **********\n");
	    // ****************************************************************************
	    // retry failed area urls + event details and retries

	    eventLogger.appendLogEntry("********** Begin Failed Area Retry : " + url + " **********");

	    AtlasUpcomingScrape upcomingScrapeRetry = new AtlasUpcomingScrape(url, 10, eventLogger);
	    upcomingScrapeRetry.execute();

	    List<String> eventLinksRetry = upcomingScrapeRetry.getEventLinks();
	    if (eventLinksRetry.size() > 0) {

		eventLogger.appendLogEntry(
			"********** Success getting Event Links : " + url + " **********\n");

		AtlasDetailsScrape detailScrape = new AtlasDetailsScrape(eventLinksRetry).execute();

		final List<TourneyDetails> eventDetails = detailScrape.getEventDetails();
		if (eventDetails.size() > 0) {

		    eventLogger.appendLogEntry(
			    "********** Success getting Event Details : " + url + " **********\n");

		    for (TourneyDetails tourneyDetails : eventDetails) {
			try {
			    TourneyDetailService.getInstance().updateEvent(tourneyDetails);
			} catch (IOException | GeneralSecurityException e) {
			    e.printStackTrace();
			}
		    }
		} else {
		    eventLogger.appendLogEntry(
			    "********** Failed to get Event Details : " + url + " **********\n");
		}

		List<String> failedEventUrls = detailScrape.getFailedUrls();
		if (failedEventUrls.size() > 0) {
		    eventLogger.appendLogEntry("********** Begin Failed Events Retry **********");

		    AtlasDetailsScrape detailScrapeRetry = new AtlasDetailsScrape(failedEventUrls);
		    detailScrapeRetry.execute();

		    final List<TourneyDetails> eventDetailsRetry = detailScrapeRetry.getEventDetails();
		    if (eventDetailsRetry.size() > 0) {

			eventLogger.appendLogEntry(
				"********** Success getting Event Details on Retry **********\n");

			for (TourneyDetails tourneyDetails : eventDetailsRetry) {
			    try {
				TourneyDetailService.getInstance().updateEvent(tourneyDetails);
			    } catch (IOException | GeneralSecurityException e) {
				e.printStackTrace();
			    }
			}
		    } else {
			eventLogger.appendLogEntry(
				"********** Failed to get Event Details on Retry **********\n");
		    }
		}
	    }
	}
	eventLogger.closeFile();
	
    }
}










































