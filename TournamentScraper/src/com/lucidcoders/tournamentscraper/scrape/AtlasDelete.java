package com.lucidcoders.tournamentscraper.scrape;

import com.lucidcoders.tournamentscraper.gae.TourneyDetailService;

public class AtlasDelete {

    public void execute() {
	TourneyDetailService.getInstance().removeAllEvents();
    }
}
