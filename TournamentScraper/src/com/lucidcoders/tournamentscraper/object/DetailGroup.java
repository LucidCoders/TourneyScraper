package com.lucidcoders.tournamentscraper.object;


public enum DetailGroup {

    TOURNAMENT_INFO("Tournament Info"),
    REGISTRATION("Registration"),
    BUYIN_DETAILS("Buy-In Details"),
    FORMAT("Format"),
    SIZE("Size"),
    STRUCTURE("Structure"),
    OTHER_INFO("Other Info");

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
    
    public enum RegistrationField {
	
	START_TIME("Start Time"),
	REGISTRATION_OPENS("Registration Opens"),
	REGISTRATION_CLOSES("Registration Closes");
	
	private String fieldName;
	
	private RegistrationField(String fieldName) {
	    this.fieldName = fieldName;
	}
	
	public String getFieldName() {
	    return fieldName;
	}
    }
    
    
    /************** Inner Class ****************/
    
    public enum BuyInDetailsField {
	
	TOTAL_BUYIN("Total Buy-In"),
	ENTRY_FEE("Entry Fee"),
	ADMIN_FEE("Admin Fee");
	
	private String fieldName;
	
	private BuyInDetailsField(String fieldName) {
	    this.fieldName = fieldName;
	}
	
	public String getFieldName() {
	    return fieldName;
	}
    }
    
    /************** Inner Class ****************/
    
    public enum FormatField {
	
	STARTING_CHIPS("Starting Chips"),
	STAFF_BONUS("Staff Bonus"),
	STAFF_BONUS_CHIPS("Staff Bonus Chips"),
	STARTING_BLINDS("Starting Blinds"),
	RE_ENTRY("Re-Entry"),
	RE_ENTRY_COST("Re-Entry Cost"),
	RE_ENTRY_CHIPS("Re-Entry Chips"),
	REBUYS("Rebuys"),
	REBUY_COST("Rebuy Cost"),
	REBUY_CHIPS("Rebuy Chips"),
	ADDONS("Addons"),
	ADDON_COST("Addon Cost"),
	ADDON_CHIPS("Addon Chips"),
	BOUNTIES("Bounties"),
	BOUNTY_AMOUNT("Bounty Amount"),
	GUARANTEE("Guarantee");
	
	private String fieldName;
	
	private FormatField(String fieldName) {
	    this.fieldName = fieldName;
	}
	
	public String getFieldName() {
	    return fieldName;
	}
    }
    
    
    /************** Inner Class ****************/
    
    public enum SizeField {
	
	GUARANTEE("Guarantee"),
	ADDED_MONEY("Added money");
	
	private String fieldName;
	
	private SizeField(String fieldName) {
	    this.fieldName = fieldName;
	}
	
	public String getFieldName() {
	    return fieldName;
	}
    }
    
    /************** Inner Class ****************/
    
    public enum StructureField {
	
	LEVEL_TIME("Level Time"),
	BREAK_LENGTH("Break Length"),
	BREAK_FREQUENCY("Break Frequency"),
	BLIND_STRUCTURE("Blind Structure"),
	STRUCTURE_NOTES("Structure Notes");
	
	private String fieldName;
	
	private StructureField(String fieldName) {
	    this.fieldName = fieldName;
	}
	
	public String getFieldName() {
	    return fieldName;
	}
    }
    
    /************** Inner Class ****************/
    
    public enum OtherInfoField {
	
	GENERAL_NOTES("General Notes");
	
	private String fieldName;
	
	private OtherInfoField(String fieldName) {
	    this.fieldName = fieldName;
	}
	
	public String getFieldName() {
	    return fieldName;
	}
	
    }
}
















