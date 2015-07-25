package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;

public class BaseQueryResponse {

    private Integer offset;
    private List<String> cookies = new ArrayList<String>();
    private String connectorVersionGuid;
    private String connectorGuid;
    private String pageUrl;
    private List<OutputProperty> outputProperties = new ArrayList<OutputProperty>();

    public Integer getOffset() {
	return offset;
    }

    public void setOffset(Integer offset) {
	this.offset = offset;
    }

    public List<String> getCookies() {
	return cookies;
    }

    public void setCookies(List<String> cookies) {
	this.cookies = cookies;
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

    /********************** Inner Classes ***********************/

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
