package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucidcoders.tournamentscraper.gae.CasinoService;
import com.lucidcoders.tournamentscraper.gae.SeriesService;
import com.lucidcoders.tournamentscraper.gae.TourneyDetailService;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;
import com.lucidcoders.tournamentscraper.util.Util;
import com.lucidcoders.tourneyspot.backend.casinoApi.model.Casino;
import com.lucidcoders.tourneyspot.backend.seriesApi.model.Series;
import com.lucidcoders.tourneyspot.backend.tourneyDetailApi.model.TourneyDetails;

@SuppressWarnings("unused")
public class AtlasQuery {
    
    private ScrapeLogger logger;
    
    public void execute() {
	logger = ScrapeLogger.getInstance();
	if (!logger.initialize()) return;
	logger.writeToLog("******************************************* ATLAS QUERY LOG *******************************************");
	logger.appendToLog("*******************************************************************************************************\n");

	listSeriesEvents();
	
	logger.closeFile();
    }
    
    private void findCasino(String casinoId) {
//	String casinoId = "ameristar-vicksburg";
	Casino casino;
	
	try {	    
	    casino = CasinoService.getInstance().findCasino(casinoId);
	} catch (IOException | GeneralSecurityException e) {
	    e.printStackTrace();
	    return;
	}
	
	if (casino != null) {
	    Gson gson = new GsonBuilder().create();
	    logger.appendLogEntry(gson.toJson(casino, Casino.class) + "\n");
	} else {
	    logger.appendLogEntry("Failed or No Results\n");
	}
    }
    
    private void listCasinos() {
	List<Casino> casinos;
	
	try {	    
	    casinos = CasinoService.getInstance().listCasinos();	    
	} catch (IOException | GeneralSecurityException e) {
	    e.printStackTrace();
	    return;
	}
	
	if (casinos != null && casinos.size() > 0) {
	    Gson gson = new GsonBuilder().create();
	    for (Casino casino : casinos) {
		logger.appendLogEntry(gson.toJson(casino, Casino.class) + "\n");
	    }
	} else {
	    logger.appendLogEntry("Failed or No Results\n");
	}
    }
    
    private void findSeries() {
	String seriesId = "100-000-guaranteed-poker-tournament-sands-casino-bethlehem-2015";	
	Series series;
	
	try {	    
	    series = SeriesService.getInstance().findSeries(seriesId);
	} catch (IOException | GeneralSecurityException e) {
	    e.printStackTrace();
	    return;
	}
	
	if (series != null) {
	    Gson gson = new GsonBuilder().create();
	    logger.appendLogEntry(gson.toJson(series, Series.class) + "\n");
	} else {
	    logger.appendLogEntry("Failed or No Results\n");
	}
    }
    
    private void listSeries() {
	String casino = "sands-casino-bethlehem";
	
	List<Series> seriesList;
	try {
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.add(Calendar.DATE, 11);
	    Date date = c.getTime();
	    
	    seriesList = SeriesService.getInstance()
		    .listSeries(casino, Util.dateToDateTimeDateOnly(date));
	} catch (IOException | GeneralSecurityException e) {
	    e.printStackTrace();
	    return;
	}
	
	if (seriesList != null && seriesList.size() > 0) {

	    Gson gson = new GsonBuilder().create();
	    for (Series series : seriesList) {
		logger.appendLogEntry(gson.toJson(series, Series.class)	+ "\n");
	    }
	} else {
	    logger.appendLogEntry("Failed or No Results\n");
	}
    }
    
    private void listEvents() {
	String casino ="potawatomi-casino-milwaukee";
	
	List<TourneyDetails> eventDetails;
	try {
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.add(Calendar.DATE, 11);
	    Date date = c.getTime();
	    
	    eventDetails = TourneyDetailService.getInstance()
		    .listEvents(casino, Util.dateToDateTimeDateOnly(date));
	} catch (IOException | GeneralSecurityException e) {
	    e.printStackTrace();
	    return;
	}
	
	if (eventDetails != null && eventDetails.size() > 0) {

	    Gson gson = new GsonBuilder().create();
	    for (TourneyDetails eventDetail : eventDetails) {
		logger.appendLogEntry(eventDetail.getAddressUrl());
		logger.appendLogEntry(gson.toJson(eventDetail, TourneyDetails.class)
			+ "\n");
	    }
	} else {
	    logger.appendLogEntry("Failed or No Results\n");
	}
    }
    
    private void listSeriesEvents() {
	String series = "11th-annual-arizona-state-poker-championship-talking-stick-resort-scottsdale-2015";
	List<TourneyDetails> eventDetails;
	try {
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.add(Calendar.DATE, 0);
	    Date date = c.getTime();
	    
	    eventDetails = TourneyDetailService.getInstance()
		    .listSeriesEvents(series, Util.dateToDateTimeDateOnly(date));
	} catch (IOException | GeneralSecurityException e) {
	    e.printStackTrace();
	    return;
	}
	
	if (eventDetails != null && eventDetails.size() > 0) {

	    Gson gson = new GsonBuilder().create();
	    for (TourneyDetails eventDetail : eventDetails) {
		logger.appendLogEntry(eventDetail.getAddressUrl());// TODO figure out why there are spaces in the url
		logger.appendLogEntry(gson.toJson(eventDetail, TourneyDetails.class)
			+ "\n");
	    }
	} else {
	    logger.appendLogEntry("Failed or No Results\n");
	}
    }
}






























