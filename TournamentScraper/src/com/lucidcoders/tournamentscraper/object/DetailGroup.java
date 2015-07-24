package com.lucidcoders.tournamentscraper.object;

import java.util.ArrayList;
import java.util.List;

public enum DetailGroup {

    TOURNAMENT_INFO("Tournament Info", new ArrayList<String>() {
	private static final long serialVersionUID = 1L;
	{
	    add("Event Number");
	    add("Event Name");
	    add("Event Type");
	    add("Game Type");
	    add("Event Start Date");
	    add("Starting Flights");
	    add("Event Type");
	}
    });

    private String groupName;
    private List<String> groupParams = new ArrayList<String>();
    
    private DetailGroup(String groupName, List<String> groupParams) {
	this.groupName = groupName;
	this.groupParams = groupParams;
    }

    public String getGroupName() {
	return groupName;
    }

    public List<String> getGroupParams() {
	return groupParams;
    }
}
