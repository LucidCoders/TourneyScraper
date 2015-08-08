package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.lucidcoders.tournamentscraper.gae.CasinoService;
import com.lucidcoders.tournamentscraper.rest.response.AtlasPokerRoomsResponse.Result;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;
import com.lucidcoders.tourneyspot.backend.casinoApi.model.Casino;

public class AtlasCasinoFullScrapeMultiThread {

public void execute() {	
	ScrapeLogger logger = ScrapeLogger.getInstance();
	if (!logger.initialize()) return;
	logger.writeToLog("**************************************** ATLAS CASINO SCRAPE LOG ****************************************");
	logger.appendToLog("*********************************************************************************************************\n");

	List<String> areaUrls = new AtlasAreasScrape().execute().getAreaUrls();
	if (areaUrls.size() > 0) {
	    logger.appendLogEntry("********** Success getting Area Urls **********\n");

	    int count = 1;
	    for (String url : areaUrls) {
		
		final int counter = count;
		final String threadUrl = url;

		Thread thread = new Thread(new Runnable() {

		    @Override
		    public void run() {
			ScrapeLogger threadLogger = new ScrapeLogger("CasinoScrape" + counter);
			threadLogger.initialize();
			threadLogger.writeToLog(
				"**************************************** ATLAS CASINO SCRAPE LOG ****************************************");
			threadLogger.appendToLog(
				"*********************************************************************************************************\n");
			
			AtlasPokerRoomsScrape pokerRoomsScrape = new AtlasPokerRoomsScrape(threadUrl, threadLogger);
			pokerRoomsScrape.execute();
			
			List<Result> pokerRooms = pokerRoomsScrape.getPokerRooms();
			if (pokerRooms.size() > 0) {
			    threadLogger.appendLogEntry("********** Success getting Poker Room Links : " + threadUrl + " **********\n");

			    AtlasCasinoScrape casinoScrape = new AtlasCasinoScrape(pokerRooms, threadLogger).execute();

			    List<Casino> casinos = casinoScrape.getCasinos();
			    if (casinos.size() > 0) {
				threadLogger.appendLogEntry("********** Success getting Casinos **********\n");

				for (Casino casino : casinos) {
				    try {
					CasinoService.getInstance().updateCasino(casino);
				    } catch (IOException | GeneralSecurityException e) {
					e.printStackTrace();
				    }
				}
			    } else {
				threadLogger.appendLogEntry("********** Failed getting Casinos **********\n");
			    }

			    List<Result> failedRooms = casinoScrape.getFailedRooms();
			    if (failedRooms.size() > 0) {
				threadLogger.appendLogEntry("********** Begin Failed Casinos Retry **********");

				AtlasCasinoScrape casinoScrapeRetry = new AtlasCasinoScrape(failedRooms,threadLogger).execute();

				List<Casino> casinosRetry = casinoScrapeRetry.getCasinos();
				if (casinosRetry.size() > 0) {
				    threadLogger.appendLogEntry("********** Success getting Casinos on Retry **********\n");

				    for (Casino casino : casinosRetry) {
					try {
					    CasinoService.getInstance().updateCasino(casino);
					} catch (IOException | GeneralSecurityException e) {
					    e.printStackTrace();
					}
				    }
				} else {
				    threadLogger.appendLogEntry("********** Failed getting Casinos on Retry **********\n");
				}
			    }
			} else {
			    // retry same url
			    threadLogger.appendLogEntry("********** Failed getting Poker Room Links : " + threadUrl + " **********\n");
			    
			    threadLogger.appendLogEntry("********** Begin getting Poker Room Links Retry : " + threadUrl + " **********");

			    AtlasPokerRoomsScrape pokerRoomsScrapeRetry = new AtlasPokerRoomsScrape(threadUrl, threadLogger);
			    pokerRoomsScrapeRetry.execute();

			    List<Result> pokerRoomsRetry = pokerRoomsScrapeRetry.getPokerRooms();
			    if (pokerRoomsRetry.size() > 0) {
				threadLogger.appendLogEntry("********** Success getting Poker Room Links on Retry : " + threadUrl + " **********\n");

				AtlasCasinoScrape casinoScrape = new AtlasCasinoScrape(pokerRoomsRetry, threadLogger).execute();

				List<Casino> casinos = casinoScrape.getCasinos();
				if (casinos.size() > 0) {
				    threadLogger.appendLogEntry("********** Success getting Casinos **********\n");

				    for (Casino casino : casinos) {
					try {
					    CasinoService.getInstance().updateCasino(casino);
					} catch (IOException | GeneralSecurityException e) {
					    e.printStackTrace();
					}
				    }
				} else {
				    threadLogger.appendLogEntry("********** Failed getting Casinos **********\n");
				}

				List<Result> failedRooms = casinoScrape.getFailedRooms();
				if (failedRooms.size() > 0) {
				    threadLogger.appendLogEntry("********** Begin Failed Casinos Retry **********");

				    AtlasCasinoScrape casinoScrapeRetry = new AtlasCasinoScrape(failedRooms, threadLogger).execute();

				    List<Casino> casinosRetry = casinoScrapeRetry.getCasinos();
				    if (casinosRetry.size() > 0) {
					threadLogger.appendLogEntry("********** Success getting Casinos on Retry **********\n");

					for (Casino casino : casinosRetry) {
					    try {
						CasinoService.getInstance().updateCasino(casino);
					    } catch (IOException | GeneralSecurityException e) {
						e.printStackTrace();
					    }
					}
				    } else {
					threadLogger.appendLogEntry("********** Failed getting Casinos on Retry **********\n");
				    }
				}
			    } else {
				threadLogger.appendLogEntry("********** Failed getting Poker Room Links on Retry : " + threadUrl + " **********\n");
			    }
			}
			threadLogger.closeFile();
		    }
		});
		thread.start();
		
		try {
		    Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		count++;
	    }
	} else {
	    logger.appendLogEntry("********** Failed to get Area Urls **********\n");
	}
	logger.closeFile();
    }
}




























