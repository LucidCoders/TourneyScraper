package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AtlasAreasResponse extends BaseQueryResponse {
    
    private List<Result> results = new ArrayList<Result>();    

    public List<Result> getResults() {
	return results;
    }

    public void setResults(List<Result> results) {
	this.results = results;
    }

    /********************** Inner Classes ***********************/
    
    public class Result {

	private List<String> area = new ArrayList<String>();
	@SerializedName("area/_source")
	private List<String> areaSource = new ArrayList<String>();
	@SerializedName("area/_text")
	private List<String> areaText = new ArrayList<String>();

	public List<String> getArea() {
	    return area;
	}

	public void setArea(List<String> area) {
	    this.area = area;
	}

	public List<String> getAreaSource() {
	    return areaSource;
	}

	public void setAreaSource(List<String> areaSource) {
	    this.areaSource = areaSource;
	}

	public List<String> getAreaText() {
	    return areaText;
	}

	public void setAreaText(List<String> areaText) {
	    this.areaText = areaText;
	}

    }
}
