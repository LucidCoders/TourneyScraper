package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucidcoders.tournamentscraper.gae.TourneyDetailImport;
import com.lucidcoders.tournamentscraper.object.DetailGroup;
import com.lucidcoders.tournamentscraper.object.DetailGroup.BuyInDetailsField;
import com.lucidcoders.tournamentscraper.object.DetailGroup.FormatField;
import com.lucidcoders.tournamentscraper.object.DetailGroup.OtherInfoField;
import com.lucidcoders.tournamentscraper.object.DetailGroup.RegistrationField;
import com.lucidcoders.tournamentscraper.object.DetailGroup.SizeField;
import com.lucidcoders.tournamentscraper.object.DetailGroup.StructureField;
import com.lucidcoders.tournamentscraper.object.DetailGroup.TournamentInfoField;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.AtlasDetailsResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasDetailsResponse.Result;
import com.lucidcoders.tournamentscraper.util.Util;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.BuyInDetails;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.Format;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.OtherInfo;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.Registration;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.Size;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.Structure;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.TournamentInfo;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.TourneyDetails;

public class AtlasDetailsScrape {

    private String mUrl;

    public void execute() throws URISyntaxException, IOException {

	mUrl = "http://www.pokeratlas.com/poker-tournament/ballys-las-vegas-75-800pm-nl-holdem-poker-tournament?topid=84550-2015-07-26";
	ImportIoRequest atlasDetailsRequest = new ImportIoRequest(mUrl);

	HttpResponse response = atlasDetailsRequest.queryGet(Extractor.ATLAS_DETAILS_5_FIELDS);

	if (response.getStatusLine().getStatusCode() == 200) {

	    Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("EEEE, MMM d, yyyy").create();

	    AtlasDetailsResponse detailsResponse = gson.fromJson(atlasDetailsRequest.getResult(),
		    AtlasDetailsResponse.class);

	    if (detailsResponse.getResults().size() == 1) {
		response = atlasDetailsRequest.queryGet(Extractor.ATLAS_DETAILS_6_FIELDS);

		if (response.getStatusLine().getStatusCode() == 200) {
		    detailsResponse = gson.fromJson(atlasDetailsRequest.getResult(), AtlasDetailsResponse.class);
		}
	    }

	    TourneyDetails tourneyDetails = buildTourneyDetails(detailsResponse);
	    new TourneyDetailImport(tourneyDetails).execute();

	    System.out.println(gson.toJson(tourneyDetails, TourneyDetails.class));
	}
    }

    private TourneyDetails buildTourneyDetails(AtlasDetailsResponse detailsResponse) {

	TourneyDetails tourneyDetails = new TourneyDetails();
	String atlasId = mUrl.substring(mUrl.indexOf("topid=")).replace("topid=", "");
	tourneyDetails.setAtlasId(atlasId);

	boolean firstPass = true;

	for (Result eachResult : detailsResponse.getResults()) {

	    if (firstPass) {

		buildEventDetails(tourneyDetails, eachResult);
		firstPass = false;

	    } else if (eachResult.getDetailGroup().equalsIgnoreCase(DetailGroup.TOURNAMENT_INFO.getGroupName())) {

		buildTournamentInfo(tourneyDetails, eachResult);

	    } else if (eachResult.getDetailGroup().equalsIgnoreCase(DetailGroup.REGISTRATION.getGroupName())) {

		buildRegistration(tourneyDetails, eachResult);

	    } else if (eachResult.getDetailGroup().equalsIgnoreCase(DetailGroup.BUYIN_DETAILS.getGroupName())) {

		buildBuyinDetails(tourneyDetails, eachResult);

	    } else if (eachResult.getDetailGroup().equalsIgnoreCase(DetailGroup.FORMAT.getGroupName())) {

		buildFormat(tourneyDetails, eachResult);

	    } else if (eachResult.getDetailGroup().equalsIgnoreCase(DetailGroup.SIZE.getGroupName())) {

		buildSize(tourneyDetails, eachResult);

	    } else if (eachResult.getDetailGroup().equalsIgnoreCase(DetailGroup.STRUCTURE.getGroupName())) {

		buildStructure(tourneyDetails, eachResult);

	    } else if (eachResult.getDetailGroup().equalsIgnoreCase(DetailGroup.OTHER_INFO.getGroupName())) {

		buildOtherInfo(tourneyDetails, eachResult);

	    }
	}

	return tourneyDetails;
    }

    private void buildEventDetails(TourneyDetails tourneyDetails, Result eachResult) {

	tourneyDetails.setCasinoName(eachResult.getCasinoText());
	tourneyDetails.setEventName(eachResult.getEventName());
	tourneyDetails.setSeriesName(eachResult.getSeriesText());
	tourneyDetails.setBuyIn(eachResult.getBuyIn());
	tourneyDetails.setEventDate(Util.dateToDateTime(eachResult.getEventDate()));
	tourneyDetails.setEventTime(Util.formatEventTime(eachResult.getEventTime()));
	tourneyDetails.setAddressText(eachResult.getAddressText());
	tourneyDetails.setAddressUrl(eachResult.getAddressSource());
    }

    private void buildTournamentInfo(TourneyDetails tourneyDetails, Result eachResult) {

	List<String> values = new ArrayList<String>();
	TournamentInfoField currentField = null;
	TournamentInfo tournamentInfo = new TournamentInfo();

	for (String string : eachResult.getDetails()) {

	    if (string.equalsIgnoreCase(TournamentInfoField.EVENT_NUMBER.getFieldName())) {

		currentField = TournamentInfoField.EVENT_NUMBER;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.EVENT_NAME.getFieldName())) {

		currentField = TournamentInfoField.EVENT_NAME;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.EVENT_TYPE.getFieldName())) {

		currentField = TournamentInfoField.EVENT_TYPE;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.GAME_TYPE.getFieldName())) {

		currentField = TournamentInfoField.GAME_TYPE;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.EVENT_START_DATE.getFieldName())) {

		currentField = TournamentInfoField.EVENT_START_DATE;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.STARTING_FLIGHTS.getFieldName())) {

		currentField = TournamentInfoField.STARTING_FLIGHTS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.LENGTH_OF_EVENT.getFieldName())) {

		currentField = TournamentInfoField.LENGTH_OF_EVENT;
		values = new ArrayList<String>();

	    } else if (currentField != null) {

		switch (currentField) {

		case EVENT_NUMBER:
		    values.add(string);
		    tournamentInfo.setEventNumber(values);
		    break;

		case EVENT_NAME:
		    values.add(string);
		    tournamentInfo.setEventName(values);
		    break;

		case EVENT_TYPE:
		    values.add(string);
		    tournamentInfo.setEventType(values);
		    break;

		case GAME_TYPE:
		    values.add(string);
		    tournamentInfo.setGameType(values);
		    break;

		case EVENT_START_DATE:
		    values.add(string);
		    tournamentInfo.setEventStartDate(values);
		    break;

		case STARTING_FLIGHTS:
		    values.add(string);
		    tournamentInfo.setStartingFlights(values);

		    break;

		case LENGTH_OF_EVENT:
		    values.add(string);
		    tournamentInfo.setLengthofEvent(values);
		    break;
		}
	    }

	    tourneyDetails.setTourneyInfo(tournamentInfo);
	}
    }

    private void buildRegistration(TourneyDetails tourneyDetails, Result eachResult) {

	List<String> values = new ArrayList<String>();
	RegistrationField currentField = null;
	Registration registration = new Registration();

	for (String string : eachResult.getDetails()) {

	    if (string.equalsIgnoreCase(RegistrationField.START_TIME.getFieldName())) {

		currentField = RegistrationField.START_TIME;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(RegistrationField.REGISTRATION_OPENS.getFieldName())) {

		currentField = RegistrationField.REGISTRATION_OPENS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(RegistrationField.REGISTRATION_CLOSES.getFieldName())) {

		currentField = RegistrationField.REGISTRATION_CLOSES;
		values = new ArrayList<String>();

	    } else if (currentField != null) {

		switch (currentField) {

		case START_TIME:
		    values.add(string);
		    registration.setStartTime(values);
		    break;

		case REGISTRATION_OPENS:
		    values.add(string);
		    registration.setRegistrationOpens(values);
		    break;

		case REGISTRATION_CLOSES:
		    values.add(string);
		    registration.setRegistrationCloses(values);
		    break;
		}
	    }

	    tourneyDetails.setRegistration(registration);
	}
    }

    private void buildBuyinDetails(TourneyDetails tourneyDetails, Result eachResult) {

	List<String> values = new ArrayList<String>();
	BuyInDetailsField currentField = null;
	BuyInDetails buyInDetails = new BuyInDetails();

	for (String string : eachResult.getDetails()) {

	    if (string.equalsIgnoreCase(BuyInDetailsField.TOTAL_BUYIN.getFieldName())) {

		currentField = BuyInDetailsField.TOTAL_BUYIN;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(BuyInDetailsField.ENTRY_FEE.getFieldName())) {

		currentField = BuyInDetailsField.ENTRY_FEE;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(BuyInDetailsField.ADMIN_FEE.getFieldName())) {

		currentField = BuyInDetailsField.ADMIN_FEE;
		values = new ArrayList<String>();

	    } else if (currentField != null) {

		switch (currentField) {

		case TOTAL_BUYIN:
		    values.add(string);
		    buyInDetails.setTotalBuyIn(values);
		    break;

		case ENTRY_FEE:
		    values.add(string);
		    buyInDetails.setEntryFee(values);
		    break;

		case ADMIN_FEE:
		    values.add(string);
		    buyInDetails.setAdminFee(values);
		    break;
		}
	    }
	}

	tourneyDetails.setBuyInDetails(buyInDetails);
    }

    private void buildFormat(TourneyDetails tourneyDetails, Result eachResult) {

	List<String> values = new ArrayList<String>();
	FormatField currentField = null;
	Format format = new Format();

	for (String string : eachResult.getDetails()) {

	    if (string.equalsIgnoreCase(FormatField.STARTING_CHIPS.getFieldName())) {

		currentField = FormatField.STARTING_CHIPS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.STAFF_BONUS.getFieldName())) {

		currentField = FormatField.STAFF_BONUS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.STAFF_BONUS_CHIPS.getFieldName())) {

		currentField = FormatField.STAFF_BONUS_CHIPS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.STARTING_BLINDS.getFieldName())) {

		currentField = FormatField.STARTING_BLINDS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.RE_ENTRY.getFieldName())) {

		currentField = FormatField.RE_ENTRY;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.RE_ENTRY_COST.getFieldName())) {

		currentField = FormatField.RE_ENTRY_COST;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.RE_ENTRY_CHIPS.getFieldName())) {

		currentField = FormatField.RE_ENTRY_CHIPS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.REBUYS.getFieldName())) {

		currentField = FormatField.REBUYS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.REBUY_COST.getFieldName())) {

		currentField = FormatField.REBUY_COST;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.REBUY_CHIPS.getFieldName())) {

		currentField = FormatField.REBUY_CHIPS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.ADDONS.getFieldName())) {

		currentField = FormatField.ADDONS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.ADDON_COST.getFieldName())) {

		currentField = FormatField.ADDON_COST;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.ADDON_CHIPS.getFieldName())) {

		currentField = FormatField.ADDON_CHIPS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.BOUNTIES.getFieldName())) {

		currentField = FormatField.BOUNTIES;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.BOUNTY_AMOUNT.getFieldName())) {

		currentField = FormatField.BOUNTY_AMOUNT;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(FormatField.GUARANTEE.getFieldName())) {

		currentField = FormatField.GUARANTEE;
		values = new ArrayList<String>();

	    } else if (currentField != null) {

		switch (currentField) {

		case STARTING_CHIPS:
		    values.add(string);
		    format.setStartingChips(values);
		    break;

		case STAFF_BONUS:
		    values.add(string);
		    format.setStaffBonus(values);
		    break;

		case STAFF_BONUS_CHIPS:
		    values.add(string);
		    format.setStaffBonusChips(values);
		    break;

		case STARTING_BLINDS:
		    values.add(string);
		    format.setStartingBlinds(values);
		    break;

		case RE_ENTRY:
		    values.add(string);
		    format.setReEntry(values);
		    break;

		case RE_ENTRY_COST:
		    values.add(string);
		    format.setReEntryCost(values);
		    break;

		case RE_ENTRY_CHIPS:
		    values.add(string);
		    format.setReEntryChips(values);
		    break;

		case REBUYS:
		    values.add(string);
		    format.setRebuys(values);
		    break;

		case REBUY_COST:
		    values.add(string);
		    format.setRebuyCost(values);
		    break;

		case REBUY_CHIPS:
		    values.add(string);
		    format.setRebuyChips(values);
		    break;

		case ADDONS:
		    values.add(string);
		    format.setAddons(values);
		    break;

		case ADDON_COST:
		    values.add(string);
		    format.setAddonCost(values);
		    break;

		case ADDON_CHIPS:
		    values.add(string);
		    format.setAddonChips(values);
		    break;

		case BOUNTIES:
		    values.add(string);
		    format.setBounties(values);
		    break;

		case BOUNTY_AMOUNT:
		    values.add(string);
		    format.setBountyAmount(values);
		    break;

		case GUARANTEE:
		    values.add(string);
		    format.setGuarantee(values);
		    break;
		}
	    }
	}

	tourneyDetails.setFormat(format);
    }

    private void buildSize(TourneyDetails tourneyDetails, Result eachResult) {

	List<String> values = new ArrayList<String>();
	SizeField currentField = null;
	Size size = new Size();

	for (String string : eachResult.getDetails()) {

	    if (string.equalsIgnoreCase(SizeField.GUARANTEE.getFieldName())) {

		currentField = SizeField.GUARANTEE;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(SizeField.ADDED_MONEY.getFieldName())) {

		currentField = SizeField.ADDED_MONEY;
		values = new ArrayList<String>();

	    } else if (currentField != null) {

		switch (currentField) {

		case GUARANTEE:
		    values.add(string);
		    size.setGuarantee(values);
		    break;

		case ADDED_MONEY:
		    values.add(string);
		    size.setAddedMoney(values);
		    break;
		}
	    }
	}

	tourneyDetails.setSize(size);
    }

    private void buildStructure(TourneyDetails tourneyDetails, Result eachResult) {

	List<String> values = new ArrayList<String>();
	StructureField currentField = null;
	Structure structure = new Structure();

	for (String string : eachResult.getDetails()) {

	    if (string.equalsIgnoreCase(StructureField.LEVEL_TIME.getFieldName())) {

		currentField = StructureField.LEVEL_TIME;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(StructureField.BREAK_LENGTH.getFieldName())) {

		currentField = StructureField.BREAK_LENGTH;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(StructureField.BREAK_FREQUENCY.getFieldName())) {

		currentField = StructureField.BREAK_FREQUENCY;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(StructureField.BLIND_STRUCTURE.getFieldName())) {

		currentField = StructureField.BLIND_STRUCTURE;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(StructureField.STRUCTURE_NOTES.getFieldName())) {

		currentField = StructureField.STRUCTURE_NOTES;
		values = new ArrayList<String>();

	    } else if (currentField != null) {

		switch (currentField) {

		case LEVEL_TIME:
		    values.add(string);
		    structure.setLevelTime(values);
		    break;

		case BREAK_LENGTH:
		    values.add(string);
		    structure.setBreakLength(values);
		    break;

		case BREAK_FREQUENCY:
		    values.add(string);
		    structure.setBreakFrequency(values);
		    break;

		case BLIND_STRUCTURE:
		    // currently not adding blind structure b/c I cannot get the
		    // link
		    // values.add(string);
		    // structure.setBlindStructure(values);
		    break;

		case STRUCTURE_NOTES:
		    values.add(string);
		    structure.setStructureNotes(values);
		    break;
		}
	    }
	}

	tourneyDetails.setStructure(structure);
    }

    private void buildOtherInfo(TourneyDetails tourneyDetails, Result eachResult) {

	List<String> values = new ArrayList<String>();
	OtherInfoField currentField = null;
	OtherInfo otherInfo = new OtherInfo();

	for (String string : eachResult.getDetails()) {

	    if (string.equalsIgnoreCase(OtherInfoField.GENERAL_NOTES.getFieldName())) {

		currentField = OtherInfoField.GENERAL_NOTES;
		values = new ArrayList<String>();

	    } else if (currentField != null) {

		switch (currentField) {

		case GENERAL_NOTES:
		    values.add(string);
		    otherInfo.setGeneralNotes(values);
		    break;
		}
	    }
	}

	tourneyDetails.setOtherInfo(otherInfo);
    }
}
