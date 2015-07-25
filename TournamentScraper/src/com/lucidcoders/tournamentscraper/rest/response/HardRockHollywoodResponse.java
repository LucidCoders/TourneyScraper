package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class HardRockHollywoodResponse {

    private List<Table> tables = new ArrayList<Table>();

    public List<Table> getTables() {
	return tables;
    }

    /********************** Inner Classes ***********************/

    public class Table extends BaseMagicResponse {

	private List<Result> results = new ArrayList<Result>();

	public List<Result> getResults() {
	    return results;
	}

	public void setResults(List<Result> results) {
	    this.results = results;
	}
    }

    public class Result {

	@SerializedName("date_value")
	private String dateValue;
	@SerializedName("date_value_numbers/_source")
	private String dateValueNumbersSource;
	@SerializedName("structure_link/_source")
	private String structureLinkSource;
	@SerializedName("structure_link")
	private String structureLink;
	@SerializedName("date_value_numbers")
	private Double dateValueNumbers;
	@SerializedName("time_value")
	private String timeValue;
	@SerializedName("time_value_numbers/_source")
	private String timeValueNumbersSource;
	@SerializedName("tournament_value")
	private String tournamentValue;
	@SerializedName("buyin_price/_currency")
	private String buyinPriceCurrency;
	@SerializedName("structure_link/_text")
	private String structureLinkText;
	@SerializedName("buyin_price")
	private Double buyinPrice;
	@SerializedName("buyin_price/_source")
	private String buyinPriceSource;
	@SerializedName("time_value_numbers")
	private Double timeValueNumbers;
	@SerializedName("tournament_value_prices/_currency")
	private List<String> tournamentValuePricesCurrency;
	@SerializedName("tournament_value_prices")
	private List<Double> tournamentValuePrices;
	@SerializedName("tournament_value_prices/_source")
	private List<String> tournamentValuePricesSource;

	public String getDateValue() {
	    return dateValue;
	}

	public void setDateValue(String dateValue) {
	    this.dateValue = dateValue;
	}

	public String getDateValueNumbersSource() {
	    return dateValueNumbersSource;
	}

	public void setDateValueNumbersSource(String dateValueNumbersSource) {
	    this.dateValueNumbersSource = dateValueNumbersSource;
	}

	public String getStructureLinkSource() {
	    return structureLinkSource;
	}

	public void setStructureLinkSource(String structureLinkSource) {
	    this.structureLinkSource = structureLinkSource;
	}

	public String getStructureLink() {
	    return structureLink;
	}

	public void setStructureLink(String structureLink) {
	    this.structureLink = structureLink;
	}

	public Double getDateValueNumbers() {
	    return dateValueNumbers;
	}

	public void setDateValueNumbers(Double dateValueNumbers) {
	    this.dateValueNumbers = dateValueNumbers;
	}

	public String getTimeValue() {
	    return timeValue;
	}

	public void setTimeValue(String timeValue) {
	    this.timeValue = timeValue;
	}

	public String getTimeValueNumbersSource() {
	    return timeValueNumbersSource;
	}

	public void setTimeValueNumbersSource(String timeValueNumbersSource) {
	    this.timeValueNumbersSource = timeValueNumbersSource;
	}

	public String getTournamentValue() {
	    return tournamentValue;
	}

	public void setTournamentValue(String tournamentValue) {
	    this.tournamentValue = tournamentValue;
	}

	public String getBuyinPriceCurrency() {
	    return buyinPriceCurrency;
	}

	public void setBuyinPriceCurrency(String buyinPriceCurrency) {
	    this.buyinPriceCurrency = buyinPriceCurrency;
	}

	public String getStructureLinkText() {
	    return structureLinkText;
	}

	public void setStructureLinkText(String structureLinkText) {
	    this.structureLinkText = structureLinkText;
	}

	public Double getBuyinPrice() {
	    return buyinPrice;
	}

	public void setBuyinPrice(Double buyinPrice) {
	    this.buyinPrice = buyinPrice;
	}

	public String getBuyinPriceSource() {
	    return buyinPriceSource;
	}

	public void setBuyinPriceSource(String buyinPriceSource) {
	    this.buyinPriceSource = buyinPriceSource;
	}

	public Double getTimeValueNumbers() {
	    return timeValueNumbers;
	}

	public void setTimeValueNumbers(Double timeValueNumbers) {
	    this.timeValueNumbers = timeValueNumbers;
	}

	public List<String> getTournamentValuePricesCurrency() {
	    return tournamentValuePricesCurrency;
	}

	public void setTournamentValuePricesCurrency(List<String> tournamentValuePricesCurrency) {
	    this.tournamentValuePricesCurrency = tournamentValuePricesCurrency;
	}

	public List<Double> getTournamentValuePrices() {
	    return tournamentValuePrices;
	}

	public void setTournamentValuePrices(List<Double> tournamentValuePrices) {
	    this.tournamentValuePrices = tournamentValuePrices;
	}

	public List<String> getTournamentValuePricesSource() {
	    return tournamentValuePricesSource;
	}

	public void setTournamentValuePricesSource(List<String> tournamentValuePricesSource) {
	    this.tournamentValuePricesSource = tournamentValuePricesSource;
	}
    }
}
