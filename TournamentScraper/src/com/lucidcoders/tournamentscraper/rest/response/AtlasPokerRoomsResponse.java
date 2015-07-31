package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AtlasPokerRoomsResponse extends BaseQueryResponse {

    private List<Result> results = new ArrayList<Result>();

    public List<Result> getResults() {
	return results;
    }

    public void setResults(List<Result> results) {
	this.results = results;
    }

    public class Result {

	@SerializedName("casinos/_source")
	private String casinoId;
	@SerializedName("casinos/_text")
	private String casinoName;
	@SerializedName("casinos")
	private String casinoUrl;

	public String getCasinoId() {
	    return casinoId;
	}

	public void setCasinoId(String casinoId) {
	    this.casinoId = casinoId;
	}

	public String getCasinoName() {
	    return casinoName;
	}

	public void setCasinoName(String casinoName) {
	    this.casinoName = casinoName;
	}

	public String getCasinoUrl() {
	    return casinoUrl;
	}

	public void setCasinoUrl(String casinoUrl) {
	    this.casinoUrl = casinoUrl;
	}
    }
}
