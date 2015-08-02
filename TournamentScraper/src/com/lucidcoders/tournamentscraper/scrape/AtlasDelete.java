package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.lucidcoders.tournamentscraper.gae.TourneyDetailService;

public class AtlasDelete {

    public void execute() {
	try {
	    TourneyDetailService.getInstance().removeAllEvents();
	} catch (IOException | GeneralSecurityException e) {
	    // TODO add gae logging
	    e.printStackTrace();
	}
    }
}
