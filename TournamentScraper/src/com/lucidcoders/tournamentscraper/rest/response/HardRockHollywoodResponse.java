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
//{"tables":[{"guid":"78c57939-39b5-4d5a-9e89-8a7c761ecb53","queryGuid":"7ee69516-306e-4d1f-89c7-81bf2f1fd25d","results":[{"date_value":"Wed, Jul 1","date_value_numbers/_source":"1","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 5-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%205-NLH(Add-On).pdf","date_value_numbers":1.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step Satellite into July 5 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":11.0},{"date_value":"Wed, Jul 1","date_value_numbers/_source":"1","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-6PM-Wednesday-NLH(RE).pdf","date_value_numbers":1.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"No Limit Hold'em (RE) - $5,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":80.0,"buyin_price/_source":"$80","tournament_value_prices/_currency":"USD","tournament_value_prices":-5000.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"- $5,000"},{"date_value":"Thu, Jul 2","date_value_numbers/_source":"2","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-11AM-Thursday-Six-Max-Deep-Stack-Turbo-NLH(RE).pdf","date_value_numbers":2.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Deep Stack Six-Max NLH(RE) - $3,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":100.0,"buyin_price/_source":"$100","tournament_value_prices/_currency":"USD","tournament_value_prices":-3000.0,"time_value_numbers":11.0,"tournament_value_prices/_source":"- $3,000"},{"date_value":"Thu, Jul 2","date_value_numbers/_source":"2","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-250-Multi-Satellite-NLH(RE).pdf","date_value_numbers":2.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"MULTI Satellite into SHRPO Event #1, #5, #10, & $1,030 Cash - 4 Packages GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":250.0,"buyin_price/_source":"$250","tournament_value_prices/_currency":"USD","tournament_value_prices":1030.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"$1,030"},{"date_value":"Fri, Jul 3","date_value_numbers/_source":"3","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-Holiday-Deep-Stack-NLH(RE).pdf","date_value_numbers":3.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Holiday Deep Stack NLH (RE) - $30,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":250.0,"buyin_price/_source":"$250","tournament_value_prices/_currency":"USD","tournament_value_prices":-30000.0,"time_value_numbers":11.0,"tournament_value_prices/_source":"- $30,000"},{"date_value":"Fri, Jul 3","date_value_numbers/_source":"3","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 5-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%205-NLH(Add-On).pdf","date_value_numbers":3.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Step Satellite into July 5 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":6.0},{"date_value":"Sat, Jul 4","date_value_numbers/_source":"4","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-Super-Stack-Semi-Turbo-NLH(RE).pdf","date_value_numbers":4.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Super Stack Semi-Turbo NLH (RE) - $10,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":160.0,"buyin_price/_source":"$160","tournament_value_prices/_currency":"USD","tournament_value_prices":-10000.0,"time_value_numbers":11.0,"tournament_value_prices/_source":"- $10,000"},{"date_value":"Sat, Jul 4","date_value_numbers/_source":"4","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-HR-Step-One-Qualifier-into-July-22-Step-Two-NLH(Add-On).pdf","date_value_numbers":4.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Step I High Roller Satellite into Step II - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":80.0,"buyin_price/_source":"$80","time_value_numbers":6.0},{"date_value":"Sun, Jul 5","date_value_numbers/_source":"5","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-Championship-Mega-Satellite-NLH(RE).pdf","date_value_numbers":5.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"SHRPO $5M GTD Championship Mega Satellite - 5 seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":570.0,"buyin_price/_source":"$570","tournament_value_prices/_currency":"USD","tournament_value_prices":5.0,"time_value_numbers":11.0,"tournament_value_prices/_source":"$5"},{"date_value":"Sun, Jul 5","date_value_numbers/_source":"5","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-6PM-Sunday-NLH(RE).pdf","date_value_numbers":5.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"No Limit Hold'em (RE) - $5,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":110.0,"buyin_price/_source":"$110","tournament_value_prices/_currency":"USD","tournament_value_prices":-5000.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"- $5,000"},{"date_value":"Mon, Jul 6","date_value_numbers/_source":"6","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 12-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%2012-NLH(Add-On).pdf","date_value_numbers":6.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step Satellite into July 12 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":11.0},{"date_value":"Mon, Jul 6","date_value_numbers/_source":"6","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-6PM-Monday-Big-Stack-NLH(RE).pdf","date_value_numbers":6.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Big Stack No Limit Hold'em (RE) - $10,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":250.0,"buyin_price/_source":"$250","tournament_value_prices/_currency":"USD","tournament_value_prices":-10000.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"- $10,000"},{"date_value":"Tue, Jul 7","date_value_numbers/_source":"7","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-HR-Step-One-Qualifier-into-July-22-Step-Two-NLH(Add-On).pdf","date_value_numbers":7.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step I High Roller Satellite into Step II - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":80.0,"buyin_price/_source":"$80","time_value_numbers":11.0},{"date_value":"Tue, Jul 7","date_value_numbers/_source":"7","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-6PM-Tuesday-NLH(RE).pdf","date_value_numbers":7.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"No Limit Hold'em (RE) - $10,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":160.0,"buyin_price/_source":"$160","tournament_value_prices/_currency":"USD","tournament_value_prices":-10000.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"- $10,000"},{"date_value":"Wed, Jul 8","date_value_numbers/_source":"8","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 12-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%2012-NLH(Add-On).pdf","date_value_numbers":8.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step Satellite into July 12 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":11.0},{"date_value":"Wed, Jul 8","date_value_numbers/_source":"8","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-6PM-Wednesday-NLH(RE).pdf","date_value_numbers":8.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"No Limit Hold'em (RE) - $5,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":80.0,"buyin_price/_source":"$80","tournament_value_prices/_currency":"USD","tournament_value_prices":-5000.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"- $5,000"},{"date_value":"Thu, Jul 9","date_value_numbers/_source":"9","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-11AM-Thursday-Six-Max-Deep-Stack-Turbo-NLH(RE).pdf","date_value_numbers":9.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Deep Stack Six-Max NLH(RE) - $3,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":100.0,"buyin_price/_source":"$100","tournament_value_prices/_currency":"USD","tournament_value_prices":-3000.0,"time_value_numbers":11.0,"tournament_value_prices/_source":"- $3,000"},{"date_value":"Thu, Jul 9","date_value_numbers/_source":"9","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-350-Multi-Satellite-NLH(RE).pdf","date_value_numbers":9.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"MULTI Satellite into SHRPO Event #1, #10, #23, & $1,080 Cash - 4 Packages GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":350.0,"buyin_price/_source":"$350","tournament_value_prices/_currency":"USD","tournament_value_prices":1080.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"$1,080"},{"date_value":"Fri, Jul 10","date_value_numbers/_source":"10","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 12-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%2012-NLH(Add-On).pdf","date_value_numbers":10.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step Satellite into July 12 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":11.0},{"date_value":"Fri, Jul 10","date_value_numbers/_source":"10","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 12-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%2012-NLH(Add-On).pdf","date_value_numbers":10.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Step Satellite into July 12 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":6.0},{"date_value":"Sat, Jul 11","date_value_numbers/_source":"11","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-11AM-Saturday-Deep-Stack-NLH(RE).pdf","date_value_numbers":11.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Deep Stack NLH (RE) - $15,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":250.0,"buyin_price/_source":"$250","tournament_value_prices/_currency":"USD","tournament_value_prices":-15000.0,"time_value_numbers":11.0,"tournament_value_prices/_source":"- $15,000"},{"date_value":"Sat, Jul 11","date_value_numbers/_source":"11","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-6PM-Saturday-Deeper-Stack-Turbo-NLH(RE).pdf","date_value_numbers":11.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Deep Stack NLH (RE) - $7,500 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":125.0,"buyin_price/_source":"$125","tournament_value_prices/_currency":"USD","tournament_value_prices":-7500.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"- $7,500"},{"date_value":"Sun, Jul 12","date_value_numbers/_source":"12","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-Championship-Mega-Satellite-NLH(RE).pdf","date_value_numbers":12.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"SHRPO $5M GTD Championship Mega Satellite - 5 seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":570.0,"buyin_price/_source":"$570","tournament_value_prices/_currency":"USD","tournament_value_prices":5.0,"time_value_numbers":11.0,"tournament_value_prices/_source":"$5"},{"date_value":"Sun, Jul 12","date_value_numbers/_source":"12","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-6PM-Sunday-NLH(RE).pdf","date_value_numbers":12.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"No Limit Hold'em (RE) - $5,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":110.0,"buyin_price/_source":"$110","tournament_value_prices/_currency":"USD","tournament_value_prices":-5000.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"- $5,000"},{"date_value":"Mon, Jul 13","date_value_numbers/_source":"13","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 19-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%2019-NLH(Add-On).pdf","date_value_numbers":13.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step Satellite into July 19 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":11.0},{"date_value":"Mon, Jul 13","date_value_numbers/_source":"13","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-6PM-Monday-Big-Stack-NLH(RE).pdf","date_value_numbers":13.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Big Stack No Limit Hold'em (RE) - $10,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":250.0,"buyin_price/_source":"$250","tournament_value_prices/_currency":"USD","tournament_value_prices":-10000.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"- $10,000"},{"date_value":"Tue, Jul 14","date_value_numbers/_source":"14","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-HR-Step-One-Qualifier-into-July-22-Step-Two-NLH(Add-On).pdf","date_value_numbers":14.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step I High Roller Satellite into Step II - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":80.0,"buyin_price/_source":"$80","time_value_numbers":11.0},{"date_value":"Tue, Jul 14","date_value_numbers/_source":"14","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-6PM-Tuesday-NLH(RE).pdf","date_value_numbers":14.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"No Limit Hold'em (RE) - $10,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":160.0,"buyin_price/_source":"$160","tournament_value_prices/_currency":"USD","tournament_value_prices":-10000.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"- $10,000"},{"date_value":"Wed, Jul 15","date_value_numbers/_source":"15","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 19-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%2019-NLH(Add-On).pdf","date_value_numbers":15.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step Satellite into July 19 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":11.0},{"date_value":"Wed, Jul 15","date_value_numbers/_source":"15","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-6PM-Wednesday-NLH(RE).pdf","date_value_numbers":15.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"No Limit Hold'em (RE) - $5,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":80.0,"buyin_price/_source":"$80","tournament_value_prices/_currency":"USD","tournament_value_prices":-5000.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"- $5,000"},{"date_value":"Thu, Jul 16","date_value_numbers/_source":"16","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-11AM-Thursday-Six-Max-Deep-Stack-Turbo-NLH(RE).pdf","date_value_numbers":16.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Deep Stack Six-Max NLH(RE) - $3,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":100.0,"buyin_price/_source":"$100","tournament_value_prices/_currency":"USD","tournament_value_prices":-3000.0,"time_value_numbers":11.0,"tournament_value_prices/_source":"- $3,000"},{"date_value":"Thu, Jul 16","date_value_numbers/_source":"16","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-560-Multi-Satellite-NLH(RE).pdf","date_value_numbers":16.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"MULTI Satellite into SHRPO Event #22, #23, & $1,250 Cash - 4 Packages GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":560.0,"buyin_price/_source":"$560","tournament_value_prices/_currency":"USD","tournament_value_prices":1250.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"$1,250"},{"date_value":"Fri, Jul 17","date_value_numbers/_source":"17","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 19-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%2019-NLH(Add-On).pdf","date_value_numbers":17.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step Satellite into July 19 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":11.0},{"date_value":"Fri, Jul 17","date_value_numbers/_source":"17","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-6PM-Friday-Night-Special-NLH(RE).pdf","date_value_numbers":17.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Friday Night Special NLH (RE) - $10,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":100.0,"buyin_price/_source":"$100","tournament_value_prices/_currency":"USD","tournament_value_prices":-10000.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"- $10,000"},{"date_value":"Sat, Jul 18","date_value_numbers/_source":"18","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-11AM-Saturday-Deep-Stack-NLH(RE).pdf","date_value_numbers":18.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Deep Stack NLH (RE) - $15,000 GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":250.0,"buyin_price/_source":"$250","tournament_value_prices/_currency":"USD","tournament_value_prices":-15000.0,"time_value_numbers":11.0,"tournament_value_prices/_source":"- $15,000"},{"date_value":"Sat, Jul 18","date_value_numbers/_source":"18","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 19-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%2019-NLH(Add-On).pdf","date_value_numbers":18.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Step Satellite into July 19 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":6.0},{"date_value":"Sun, Jul 19","date_value_numbers/_source":"19","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-Championship-Mega-Satellite-NLH(RE).pdf","date_value_numbers":19.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"SHRPO $5M GTD Championship Mega Satellite - 5 seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":570.0,"buyin_price/_source":"$570","tournament_value_prices/_currency":"USD","tournament_value_prices":5.0,"time_value_numbers":11.0,"tournament_value_prices/_source":"$5"},{"date_value":"Sun, Jul 19","date_value_numbers/_source":"19","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-HR-Step-One-Qualifier-into-July-22-Step-Two-NLH(Add-On).pdf","date_value_numbers":19.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Step I High Roller Satellite into Step II - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":80.0,"buyin_price/_source":"$80","time_value_numbers":6.0},{"date_value":"Mon, Jul 20","date_value_numbers/_source":"20","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 26-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%2026-NLH(Add-On).pdf","date_value_numbers":20.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step Satellite into July 26 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":11.0},{"date_value":"Mon, Jul 20","date_value_numbers/_source":"20","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-HR-Step-One-Qualifier-into-July-22-Step-Two-NLH(Add-On).pdf","date_value_numbers":20.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Step I High Roller Satellite into Step II - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":80.0,"buyin_price/_source":"$80","time_value_numbers":6.0},{"date_value":"Tue, Jul 21","date_value_numbers/_source":"21","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 26-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%2026-NLH(Add-On).pdf","date_value_numbers":21.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step Satellite into July 26 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":11.0},{"date_value":"Tue, Jul 21","date_value_numbers/_source":"21","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-HR-Step-One-Qualifier-into-July-22-Step-Two-NLH(Add-On).pdf","date_value_numbers":21.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Step I High Roller Satellite into Step II - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":80.0,"buyin_price/_source":"$80","time_value_numbers":6.0},{"date_value":"Wed, Jul 22","date_value_numbers/_source":"22","structure_link/_source":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July 26-NLH(Add-On).pdf","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-140-Step-Qualifier-into-July%2026-NLH(Add-On).pdf","date_value_numbers":22.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"Step Satellite into July 26 Championship Mega - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":11.0},{"date_value":"Wed, Jul 22","date_value_numbers/_source":"22","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-HR-Step-Two-Satellite-into-July-25-HR-Mega-Satellite-NLH(RE).pdf","date_value_numbers":22.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"Step II High Roller Satellite into High Roller Mega Satellite - 5 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":300.0,"buyin_price/_source":"$300","time_value_numbers":6.0},{"date_value":"Thu, Jul 23","date_value_numbers/_source":"23","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-Mega-Satellite-into-500K-GTD-Poker-Open-Event-23-NLH(RE).pdf","date_value_numbers":23.0,"time_value":"4P","time_value_numbers/_source":"4","tournament_value":"SHRPO Event #23 ($1,100/$500K GTD) Mega Satellite - 10 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","tournament_value_prices/_currency":["USD","USD"],"tournament_value_prices":[1100.0,500.0],"time_value_numbers":4.0,"tournament_value_prices/_source":["$1,100","$500"]},{"date_value":"Fri, Jul 24","date_value_numbers/_source":"24","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-Mega-Satellite-into-1-Million-Poker-Open-Event-22-NLH(RE).pdf","date_value_numbers":24.0,"time_value":"4P","time_value_numbers/_source":"4","tournament_value":"SHRPO Event #22 ($2,650/$1M GTD) Mega Satellite - 10 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":300.0,"buyin_price/_source":"$300","tournament_value_prices/_currency":["USD","USD"],"tournament_value_prices":[2650.0,1.0],"time_value_numbers":4.0,"tournament_value_prices/_source":["$2,650","$1"]},{"date_value":"Sat, Jul 25","date_value_numbers/_source":"25","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-High-Roller-Mega-Satellite-into-1-Million-GTD-High-Roller-NLH(RE).pdf","date_value_numbers":25.0,"time_value":"4P","time_value_numbers/_source":"4","tournament_value":"SHRPO Event #24 ($25,400/$1M GTD) Mega Satellite - 2 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":2650.0,"buyin_price/_source":"$2,650","tournament_value_prices/_currency":["USD","USD"],"tournament_value_prices":[25400.0,1.0],"time_value_numbers":4.0,"tournament_value_prices/_source":["$25,400","$1"]},{"date_value":"Sun, Jul 26","date_value_numbers/_source":"26","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-Championship-Mega-Satellite-NLH(RE).pdf","date_value_numbers":26.0,"time_value":"4P","time_value_numbers/_source":"4","tournament_value":"SHRPO $5M GTD Championship Mega Satellite - 10 seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":570.0,"buyin_price/_source":"$570","tournament_value_prices/_currency":"USD","tournament_value_prices":5.0,"time_value_numbers":4.0,"tournament_value_prices/_source":"$5"},{"date_value":"Mon, Jul 27","date_value_numbers/_source":"27","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-90-Satellite-into-SHRPO-Event-1-NLH(Opt-Add).pdf","date_value_numbers":27.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"SHRPO Event #1 Mega Satellite - 10 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":90.0,"buyin_price/_source":"$90","time_value_numbers":11.0},{"date_value":"Mon, Jul 27","date_value_numbers/_source":"27","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-250-Multi-Satellite-NLH(RE).pdf","date_value_numbers":27.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"MULTI Satellite into SHRPO Event #1, #5, #10, & $1,030 Cash - 4 Packages GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":250.0,"buyin_price/_source":"$250","tournament_value_prices/_currency":"USD","tournament_value_prices":1030.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"$1,030"},{"date_value":"Tue, Jul 28","date_value_numbers/_source":"28","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-Mega-Satellite-into-SHRPO-Event-10-NLH(ADD-On).pdf","date_value_numbers":28.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"SHRPO Event #10 Mega Satellite - 10 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":140.0,"buyin_price/_source":"$140","time_value_numbers":11.0},{"date_value":"Tue, Jul 28","date_value_numbers/_source":"28","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-350-Multi-Satellite-NLH(RE).pdf","date_value_numbers":28.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"MULTI Satellite into SHRPO Event #1, #10, #23, & $1,080 Cash - 4 Packages GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":350.0,"buyin_price/_source":"$350","tournament_value_prices/_currency":"USD","tournament_value_prices":1080.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"$1,080"},{"date_value":"Wed, Jul 29","date_value_numbers/_source":"29","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-90-Satellite-into-SHRPO-Event-1-NLH(Opt-Add).pdf","date_value_numbers":29.0,"time_value":"11A","time_value_numbers/_source":"11","tournament_value":"SHRPO Event #1 Mega Satellite - 10 Seats GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":90.0,"buyin_price/_source":"$90","time_value_numbers":11.0},{"date_value":"Wed, Jul 29","date_value_numbers/_source":"29","structure_link":"http://seminolehardrockpokeropen.com/wp-content/uploads/2015/07/2015-July-560-Multi-Satellite-NLH(RE).pdf","date_value_numbers":29.0,"time_value":"6P","time_value_numbers/_source":"6","tournament_value":"MULTI Satellite into SHRPO Event #22, #23, & $1,250 Cash - 4 Packages GTD","buyin_price/_currency":"USD","structure_link/_text":"Details","buyin_price":560.0,"buyin_price/_source":"$560","tournament_value_prices/_currency":"USD","tournament_value_prices":1250.0,"time_value_numbers":6.0,"tournament_value_prices/_source":"$1,250"}],"connectorVersionGuid":"b5d44827-16de-4b1c-f04e-64397983bf71","connectorGuid":"316c88fc-b54f-4d3d-aadc-1df1438b2dc1","pageUrl":"http://www.seminolehardrockpokeropen.com/daily-tournament-schedule-july-2015/","outputProperties":[{"name":"date_value","type":"STRING"},{"name":"date_value_numbers","type":"DOUBLE"},{"name":"time_value","type":"STRING"},{"name":"time_value_numbers","type":"DOUBLE"},{"name":"buyin_price","type":"CURRENCY"},{"name":"tournament_value","type":"STRING"},{"name":"tournament_value_prices","type":"CURRENCY"},{"name":"structure_link","type":"URL"}],"regionXPath":"/html/body[1][for $s in concat(' ',normalize-space(@class),' ') return contains($s, ' single ') and contains($s, ' single-post ') and contains($s, ' postid-39089 ') and contains($s, ' single-format-standard ') and contains($s, ' custom-background ') and contains($s, ' group-blog ') and contains($s, ' header-image ') and contains($s, ' footer-widgets ') and contains($s, ' singular ')][not(@id)]/div[1][for $s in concat(' ',normalize-space(@class),' ') return contains($s, ' hfeed ') and contains($s, ' site ')][normalize-space(@id)='page']/div[2][contains(concat(' ',normalize-space(@class),' '),' site-main ')][normalize-space(@id)='main']/div[1][contains(concat(' ',normalize-space(@class),' '),' content-area ')][normalize-space(@id)='primary']/div[1][contains(concat(' ',normalize-space(@class),' '),' site-content ')][normalize-space(@id)='content']/article[1][for $s in concat(' ',normalize-space(@class),' ') return contains($s, ' post-39089 ') and contains($s, ' post ') and contains($s, ' type-post ') and contains($s, ' status-publish ') and contains($s, ' format-standard ') and contains($s, ' hentry ') and contains($s, ' category-daily-tournaments ') and contains($s, ' category-the-poker-room ')][normalize-space(@id)='post-39089']/div[1][contains(concat(' ',normalize-space(@class),' '),' entry-content ')][not(@id)]/table[1][for $s in concat(' ',normalize-space(@class),' ') return contains($s, ' tablepress ') and contains($s, ' tablepress-id-219 ')][normalize-space(@id)='tablepress-219']/tbody[1][contains(concat(' ',normalize-space(@class),' '),' row-hover ')][not(@id)]","resultXPath":"//td[contains(concat(' ',normalize-space(@class),' '),' column-1 ')][not(@style)]/text()[1]/../..","periodicity":0,"scores":{"rowDetector":[432],"tableDetector":[50],"regionDetector":[242],"reliability":3376.6851959228516,"normalizedRegion":724}}]}
}














