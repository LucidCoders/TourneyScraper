package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SeriesEventsResponse extends BaseQueryResponse {

    private List<SeriesEventResult> results = new ArrayList<>();

    public List<SeriesEventResult> getResults() {
	return results;
    }

    public void setResults(List<SeriesEventResult> results) {
	this.results = results;
    }

    public class SeriesEventResult {

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
