package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AtlasCasinoResponse extends BaseQueryResponse {

    private List<Result> results = new ArrayList<Result>();

    public List<Result> getResults() {
	return results;
    }

    public void setResults(List<Result> results) {
	this.results = results;
    }

    public class Result {

	private String image;
	private String website;
	private String hours;
	@SerializedName("address_link/_text")
	private String address;
	@SerializedName("casino_name")
	private List<String> casinoName = new ArrayList<String>();
	private String age;

	public String getImage() {
	    return image;
	}

	public void setImage(String image) {
	    this.image = image;
	}

	public String getWebsite() {
	    return website;
	}

	public void setWebsite(String website) {
	    this.website = website;
	}

	public String getHours() {
	    return hours;
	}

	public void setHours(String hours) {
	    this.hours = hours;
	}

	public String getAddress() {
	    return address;
	}

	public void setAddress(String address) {
	    this.address = address;
	}

	public List<String> getCasinoName() {
	    return casinoName;
	}

	public void setCasinoName(List<String> casinoName) {
	    this.casinoName = casinoName;
	}

	public String getAge() {
	    return age;
	}

	public void setAge(String age) {
	    this.age = age;
	}

    }
}
