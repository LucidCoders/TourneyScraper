package com.lucidcoders.tournamentscraper.object;

import java.util.ArrayList;
import java.util.List;

public class TourneyDetails {

    private String casinoName;
    private String eventName;
    private String seriesName;
    private String buyIn;
    private String eventDate;
    private String eventTime;
    private String addressText;
    private String addressUrl;
    private TournamentInfo tourneyInfo;
    private Registration registration;
    private BuyInDetails buyInDetails;
    private Format format;
    private Size size;
    private Structure structure;
    private OtherInfo otherInfo;

    public String getCasinoName() {
	return casinoName;
    }

    public void setCasinoName(String casinoName) {
	this.casinoName = casinoName;
    }

    public String getEventName() {
	return eventName;
    }

    public void setEventName(String eventName) {
	this.eventName = eventName;
    }

    public String getSeriesName() {
	return seriesName;
    }

    public void setSeriesName(String seriesName) {
	this.seriesName = seriesName;
    }

    public String getBuyIn() {
	return buyIn;
    }

    public void setBuyIn(String buyIn) {
	this.buyIn = buyIn;
    }

    public String getEventDate() {
	return eventDate;
    }

    public void setEventDate(String eventDate) {
	this.eventDate = eventDate;
    }

    public String getEventTime() {
	return eventTime;
    }

    public void setEventTime(String eventTime) {
	this.eventTime = eventTime;
    }

    public String getAddressText() {
	return addressText;
    }

    public void setAddressText(String addressText) {
	this.addressText = addressText;
    }

    public String getAddressUrl() {
	return addressUrl;
    }

    public void setAddressUrl(String addressUrl) {
	this.addressUrl = addressUrl;
    }

    public TournamentInfo getTourneyInfo() {
	return tourneyInfo;
    }

    public void setTourneyInfo(TournamentInfo tourneyInfo) {
	this.tourneyInfo = tourneyInfo;
    }

    public Registration getRegistration() {
	return registration;
    }

    public void setRegistration(Registration registration) {
	this.registration = registration;
    }

    public BuyInDetails getBuyInDetails() {
	return buyInDetails;
    }

    public void setBuyInDetails(BuyInDetails buyInDetails) {
	this.buyInDetails = buyInDetails;
    }

    public Format getFormat() {
	return format;
    }

    public void setFormat(Format format) {
	this.format = format;
    }

    public Size getSize() {
	return size;
    }

    public void setSize(Size size) {
	this.size = size;
    }

    public Structure getStructure() {
	return structure;
    }

    public void setStructure(Structure structure) {
	this.structure = structure;
    }

    public OtherInfo getOtherInfo() {
	return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
	this.otherInfo = otherInfo;
    }

    /********************** Inner Class ***********************/

    public class TournamentInfo {

	private List<String> eventNumber = new ArrayList<String>();
	private List<String> eventName = new ArrayList<String>();
	private List<String> eventType = new ArrayList<String>();
	private List<String> gameType = new ArrayList<String>();
	private List<String> eventStartDate = new ArrayList<String>();
	private List<String> startingFlights = new ArrayList<String>();
	private List<String> lengthofEvent = new ArrayList<String>();

	public List<String> getEventNumber() {
	    return eventNumber;
	}

	public void setEventNumber(List<String> eventNumber) {
	    this.eventNumber = eventNumber;
	}

	public List<String> getEventName() {
	    return eventName;
	}

	public void setEventName(List<String> eventName) {
	    this.eventName = eventName;
	}

	public List<String> getEventType() {
	    return eventType;
	}

	public void setEventType(List<String> eventType) {
	    this.eventType = eventType;
	}

	public List<String> getGameType() {
	    return gameType;
	}

	public void setGameType(List<String> gameType) {
	    this.gameType = gameType;
	}

	public List<String> getEventStartDate() {
	    return eventStartDate;
	}

	public void setEventStartDate(List<String> eventStartDate) {
	    this.eventStartDate = eventStartDate;
	}

	public List<String> getStartingFlights() {
	    return startingFlights;
	}

	public void setStartingFlights(List<String> startingFlights) {
	    this.startingFlights = startingFlights;
	}

	public List<String> getLengthofEvent() {
	    return lengthofEvent;
	}

	public void setLengthofEvent(List<String> lengthofEvent) {
	    this.lengthofEvent = lengthofEvent;
	}
    }

    /********************** Inner Class ***********************/

    public class Registration {
	private List<String> startTime = new ArrayList<String>();
	private List<String> registrationOpens = new ArrayList<String>();
	private List<String> registrationCloses = new ArrayList<String>();

	public List<String> getStartTime() {
	    return startTime;
	}

	public void setStartTime(List<String> startTime) {
	    this.startTime = startTime;
	}

	public List<String> getRegistrationOpens() {
	    return registrationOpens;
	}

	public void setRegistrationOpens(List<String> registrationOpens) {
	    this.registrationOpens = registrationOpens;
	}

	public List<String> getRegistrationCloses() {
	    return registrationCloses;
	}

	public void setRegistrationCloses(List<String> registrationCloses) {
	    this.registrationCloses = registrationCloses;
	}
    }

    /********************** Inner Class ***********************/

    public class BuyInDetails {
	private List<String> totalBuyIn = new ArrayList<String>();
	private List<String> entryFee = new ArrayList<String>();
	private List<String> adminFee = new ArrayList<String>();

	public List<String> getTotalBuyIn() {
	    return totalBuyIn;
	}

	public void setTotalBuyIn(List<String> totalBuyIn) {
	    this.totalBuyIn = totalBuyIn;
	}

	public List<String> getEntryFee() {
	    return entryFee;
	}

	public void setEntryFee(List<String> entryFee) {
	    this.entryFee = entryFee;
	}

	public List<String> getAdminFee() {
	    return adminFee;
	}

	public void setAdminFee(List<String> adminFee) {
	    this.adminFee = adminFee;
	}
    }

    /********************** Inner Class ***********************/

    public class Format {
	private List<String> startingChips = new ArrayList<String>();
	private List<String> staffBonus = new ArrayList<String>();
	private List<String> staffBonusChips = new ArrayList<String>();
	private List<String> startingBlinds = new ArrayList<String>();
	private List<String> reEntry = new ArrayList<String>();
	private List<String> reEntryCost = new ArrayList<String>();
	private List<String> reEntryChips = new ArrayList<String>();
	private List<String> rebuys = new ArrayList<String>();
	private List<String> rebuyCost = new ArrayList<String>();
	private List<String> rebuyChips = new ArrayList<String>();
	private List<String> addons = new ArrayList<String>();
	private List<String> addonCost = new ArrayList<String>();
	private List<String> addonChips = new ArrayList<String>();
	private List<String> bounties = new ArrayList<String>();
	private List<String> bountyAmount = new ArrayList<String>();
	private List<String> guarantee = new ArrayList<String>();

	public List<String> getStartingChips() {
	    return startingChips;
	}

	public void setStartingChips(List<String> startingChips) {
	    this.startingChips = startingChips;
	}

	public List<String> getStartingBlinds() {
	    return startingBlinds;
	}

	public void setStartingBlinds(List<String> startingBlinds) {
	    this.startingBlinds = startingBlinds;
	}

	public List<String> getReEntry() {
	    return reEntry;
	}

	public void setReEntry(List<String> reEntry) {
	    this.reEntry = reEntry;
	}

	public List<String> getRebuys() {
	    return rebuys;
	}

	public void setRebuys(List<String> rebuys) {
	    this.rebuys = rebuys;
	}

	public List<String> getAddons() {
	    return addons;
	}

	public void setAddons(List<String> addons) {
	    this.addons = addons;
	}

	public List<String> getBounties() {
	    return bounties;
	}

	public void setBounties(List<String> bounties) {
	    this.bounties = bounties;
	}

	public List<String> getGuarantee() {
	    return guarantee;
	}

	public void setGuarantee(List<String> guarantee) {
	    this.guarantee = guarantee;
	}

	public List<String> getStaffBonusChips() {
	    return staffBonusChips;
	}

	public void setStaffBonusChips(List<String> staffBonusChips) {
	    this.staffBonusChips = staffBonusChips;
	}

	public List<String> getStaffBonus() {
	    return staffBonus;
	}

	public void setStaffBonus(List<String> staffBonus) {
	    this.staffBonus = staffBonus;
	}

	public List<String> getReEntryCost() {
	    return reEntryCost;
	}

	public void setReEntryCost(List<String> reEntryCost) {
	    this.reEntryCost = reEntryCost;
	}

	public List<String> getReEntryChips() {
	    return reEntryChips;
	}

	public void setReEntryChips(List<String> reEntryChips) {
	    this.reEntryChips = reEntryChips;
	}

	public List<String> getRebuyCost() {
	    return rebuyCost;
	}

	public void setRebuyCost(List<String> rebuyCost) {
	    this.rebuyCost = rebuyCost;
	}

	public List<String> getRebuyChips() {
	    return rebuyChips;
	}

	public void setRebuyChips(List<String> rebuyChips) {
	    this.rebuyChips = rebuyChips;
	}

	public List<String> getAddonCost() {
	    return addonCost;
	}

	public void setAddonCost(List<String> addonCost) {
	    this.addonCost = addonCost;
	}

	public List<String> getAddonChips() {
	    return addonChips;
	}

	public void setAddonChips(List<String> addonChips) {
	    this.addonChips = addonChips;
	}

	public List<String> getBountyAmount() {
	    return bountyAmount;
	}

	public void setBountyAmount(List<String> bountyAmount) {
	    this.bountyAmount = bountyAmount;
	}
    }

    /********************** Inner Class ***********************/

    public class Size {
	private List<String> guarantee = new ArrayList<String>();
	private List<String> addedMoney = new ArrayList<String>();

	public List<String> getGuarantee() {
	    return guarantee;
	}

	public void setGuarantee(List<String> guarantee) {
	    this.guarantee = guarantee;
	}

	public List<String> getAddedMoney() {
	    return addedMoney;
	}

	public void setAddedMoney(List<String> addedMoney) {
	    this.addedMoney = addedMoney;
	}
    }

    /********************** Inner Class ***********************/

    public class Structure {
	private List<String> levelTime = new ArrayList<String>();
	private List<String> breakLength = new ArrayList<String>();
	private List<String> breakFrequency = new ArrayList<String>();
	private List<String> blindStructure = new ArrayList<String>(); // exclude
								       // this
								       // field
	private List<String> structureNotes = new ArrayList<String>();

	public List<String> getLevelTime() {
	    return levelTime;
	}

	public void setLevelTime(List<String> levelTime) {
	    this.levelTime = levelTime;
	}

	public List<String> getBreakLength() {
	    return breakLength;
	}

	public void setBreakLength(List<String> breakLength) {
	    this.breakLength = breakLength;
	}

	public List<String> getBreakFrequency() {
	    return breakFrequency;
	}

	public void setBreakFrequency(List<String> breakFrequency) {
	    this.breakFrequency = breakFrequency;
	}

	public List<String> getStructureNotes() {
	    return structureNotes;
	}

	public void setStructureNotes(List<String> structureNotes) {
	    this.structureNotes = structureNotes;
	}

	public List<String> getBlindStructure() {
	    return blindStructure;
	}

	public void setBlindStructure(List<String> blindStructure) {
	    this.blindStructure = blindStructure;
	}
    }

    /********************** Inner Class ***********************/

    public class OtherInfo {
	private List<String> generalNotes = new ArrayList<String>();

	public List<String> getGeneralNotes() {
	    return generalNotes;
	}

	public void setGeneralNotes(List<String> generalNotes) {
	    this.generalNotes = generalNotes;
	}
    }
}
