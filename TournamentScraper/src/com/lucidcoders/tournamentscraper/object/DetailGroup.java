package com.lucidcoders.tournamentscraper.object;


public enum DetailGroup {

    TOURNAMENT_INFO("Tournament Info");

    private String groupName;
    
    private DetailGroup(String groupName) {
	this.groupName = groupName;
    }

    public String getGroupName() {
	return groupName;
    }
    
    /************** Inner Class ****************/
    public enum TournamentInfoField {
	
	EVENT_NUMBER("Event Number"),
	EVENT_NAME("Event Name"),
	EVENT_TYPE("Event Type"),
	GAME_TYPE("Game Type"),
	EVENT_START_DATE("Event Start Date"),
	STARTING_FLIGHTS("Starting Flights"),
	LENGTH_OF_EVENT("Length of Event");	
	
	private String fieldName;
	
	private TournamentInfoField(String fieldName) {
	    this.fieldName = fieldName;
	}
	
	public String getFieldName() {
	    return fieldName;
	}
	
    }
    
    
    /************** Inner Class ****************/
    
    
    /************** Inner Class ****************/
    
    
    /************** Inner Class ****************/
    
    
    /************** Inner Class ****************/
    
    
    /************** Inner Class ****************/
}
