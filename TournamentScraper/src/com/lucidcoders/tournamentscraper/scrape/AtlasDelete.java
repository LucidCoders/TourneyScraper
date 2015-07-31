package com.lucidcoders.tournamentscraper.scrape;

import com.lucidcoders.tournamentscraper.gae.TourneyDetailApi;

public class AtlasDelete {

    public void execute() {
	TourneyDetailApi.getInstance().removeAllEvents();
    }
}
