package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AtlasUpcomingResponse extends BaseQueryResponse {

    private List<Result> results = new ArrayList<Result>();

    public List<Result> getResults() {
	return results;
    }

    public void setResults(List<Result> results) {
	this.results = results;

    }

    /********************** Inner Classes ***********************/
    
    public class Result {

	@SerializedName("event_link/_text")
	private String eventLinkText;
	@SerializedName("event_link/_source")
	private String eventLinkSource;
	@SerializedName("event_link")
	private String eventLink;
	private String event;

	public String getEventLinkText() {
	    return eventLinkText;
	}

	public void setEventLinkText(String eventLinkText) {
	    this.eventLinkText = eventLinkText;
	}

	public String getEventLinkSource() {
	    return eventLinkSource;
	}

	public void setEventLinkSource(String eventLinkSource) {
	    this.eventLinkSource = eventLinkSource;
	}

	public String getEventLink() {
	    return eventLink;
	}

	public void setEventLink(String eventLink) {
	    this.eventLink = eventLink;
	}

	public String getEvent() {
	    return event;
	}

	public void setEvent(String event) {
	    this.event = event;
	}
    }
}
