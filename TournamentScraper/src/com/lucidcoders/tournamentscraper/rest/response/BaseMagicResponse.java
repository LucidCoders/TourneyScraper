package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;

public class BaseMagicResponse {

    private String guid;
    private String queryGuid;
    private String connectorVersionGuid;
    private String connectorGuid;
    private String pageUrl;
    private List<OutputProperty> outputProperties = new ArrayList<OutputProperty>();
    private String regionXPath;
    private String resultXPath;
    private Integer periodicity;
    private Scores scores;

    public String getGuid() {
	return guid;
    }

    public void setGuid(String guid) {
	this.guid = guid;
    }

    public String getQueryGuid() {
	return queryGuid;
    }

    public void setQueryGuid(String queryGuid) {
	this.queryGuid = queryGuid;
    }

    public String getConnectorVersionGuid() {
	return connectorVersionGuid;
    }

    public void setConnectorVersionGuid(String connectorVersionGuid) {
	this.connectorVersionGuid = connectorVersionGuid;
    }

    public String getConnectorGuid() {
	return connectorGuid;
    }

    public void setConnectorGuid(String connectorGuid) {
	this.connectorGuid = connectorGuid;
    }

    public String getPageUrl() {
	return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
	this.pageUrl = pageUrl;
    }

    public List<OutputProperty> getOutputProperties() {
	return outputProperties;
    }

    public void setOutputProperties(List<OutputProperty> outputProperties) {
	this.outputProperties = outputProperties;
    }

    public String getRegionXPath() {
	return regionXPath;
    }

    public void setRegionXPath(String regionXPath) {
	this.regionXPath = regionXPath;
    }

    public String getResultXPath() {
	return resultXPath;
    }

    public void setResultXPath(String resultXPath) {
	this.resultXPath = resultXPath;
    }

    public Integer getPeriodicity() {
	return periodicity;
    }

    public void setPeriodicity(Integer periodicity) {
	this.periodicity = periodicity;
    }

    public Scores getScores() {
	return scores;
    }

    public void setScores(Scores scores) {
	this.scores = scores;
    }

    /********************** Inner Classes ***********************/
    
    public class Scores {

	private List<Integer> rowDetector = new ArrayList<Integer>();
	private List<Integer> tableDetector = new ArrayList<Integer>();
	private List<Integer> regionDetector = new ArrayList<Integer>();
	private Double reliability;
	private Integer normalizedRegion;

	public List<Integer> getRowDetector() {
	    return rowDetector;
	}

	public void setRowDetector(List<Integer> rowDetector) {
	    this.rowDetector = rowDetector;
	}

	public List<Integer> getTableDetector() {
	    return tableDetector;
	}

	public void setTableDetector(List<Integer> tableDetector) {
	    this.tableDetector = tableDetector;
	}

	public List<Integer> getRegionDetector() {
	    return regionDetector;
	}

	public void setRegionDetector(List<Integer> regionDetector) {
	    this.regionDetector = regionDetector;
	}

	public Double getReliability() {
	    return reliability;
	}

	public void setReliability(Double reliability) {
	    this.reliability = reliability;
	}

	public Integer getNormalizedRegion() {
	    return normalizedRegion;
	}

	public void setNormalizedRegion(Integer normalizedRegion) {
	    this.normalizedRegion = normalizedRegion;
	}

    }

    public class OutputProperty {

	private String name;
	private String type;

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}
    }
}
