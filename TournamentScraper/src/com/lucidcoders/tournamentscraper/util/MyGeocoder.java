package com.lucidcoders.tournamentscraper.util;

import java.io.IOException;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;

public class MyGeocoder {
    
    private Double mLat = null;
    private Double mLng = null;

    public MyGeocoder execute(String address) {
	try {
	    final Geocoder geocoder = new Geocoder();
	    GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(address).setLanguage("en")
		    .getGeocoderRequest();

	    GeocodeResponse geocoderResponse = null;

	    try {
		geocoderResponse = geocoder.geocode(geocoderRequest);
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	    if (geocoderResponse != null && geocoderResponse.getResults() != null
		    && geocoderResponse.getResults().size() > 0) {
		GeocoderResult result = geocoderResponse.getResults().get(0);
		setLat(result.getGeometry().getLocation().getLat().doubleValue());
		setLng(result.getGeometry().getLocation().getLng().doubleValue());

	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return this;
    }

    public Double getLat() {
	return mLat;
    }

    public void setLat(Double mLat) {
	this.mLat = mLat;
    }

    public Double getLng() {
	return mLng;
    }

    public void setLng(Double mLng) {
	this.mLng = mLng;
    }
}






















