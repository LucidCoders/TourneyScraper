package com.lucidcoders.tournamentscraper.object;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.geonames.Timezone;
import org.geonames.WebService;

import com.google.api.client.util.Base64;
import com.lucidcoders.tournamentscraper.rest.response.AtlasCasinoResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasCasinoResponse.Result;
import com.lucidcoders.tournamentscraper.rest.response.AtlasPokerRoomsResponse;
import com.lucidcoders.tournamentscraper.util.MyGeocoder;
import com.lucidcoders.tournamentscraper.util.Util;
import com.lucidcoders.tourneyspot.backend.casinoApi.model.Casino;

public class CasinoBuilder {

    private AtlasCasinoResponse mCasinoResponse;
    private AtlasPokerRoomsResponse.Result mPokerRoom;
    
    public CasinoBuilder(AtlasCasinoResponse casinoResponse, AtlasPokerRoomsResponse.Result pokerRoom) {
	mCasinoResponse = casinoResponse;
	mPokerRoom = pokerRoom;
    }
    
    public Casino build() {
	
	Casino casino = null;
	
	List<Result> casinoResults = mCasinoResponse.getResults();
	if (casinoResults.size() > 0) {
	    
	    casino = new Casino();
	    Result casinoResult = casinoResults.get(0);
	    
	    casino.setCasinoId(mPokerRoom.getCasinoId());
	    
	    if (casinoResult.getCasinoName().get(0) != null) {
		casino.setCasinoName(casinoResult.getCasinoName().get(0));
	    } else {
		casino.setCasinoName(mPokerRoom.getCasinoName());
	    }
	    
	    if (casinoResult.getCasinoName().get(1) != null) {
		casino.setPhoneNumber(casinoResult.getCasinoName().get(1));
	    }
	    
	    casino.setAddress(casinoResult.getAddress());
	    casino.setHours(casinoResult.getHours());
	    casino.setAge(casinoResult.getAge());
	    casino.setWebsite(casinoResult.getWebsite());
	    
	    byte[] imageBytes = null;
	    try {
		imageBytes = Util.downloadImageUrl(new URL(casinoResult.getImage()));
	    } catch (MalformedURLException e) {
		// TODO Maybe add logging here
		e.printStackTrace();
	    }
	    
	    if (imageBytes != null && imageBytes.length > 0) {
		casino.setImage(new String(Base64.encodeBase64(imageBytes)));
	    }
	    
	    MyGeocoder geocoder = new MyGeocoder().execute(casinoResult.getAddress());
	    casino.setLat(geocoder.getLat());
	    casino.setLng(geocoder.getLng());
	    
	    try {
		WebService.setUserName("LucidCoders");
		Timezone timezone = WebService.timezone(geocoder.getLat(), geocoder.getLng());
		casino.setTimeZone(timezone.getTimezoneId());
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	
	return casino;
    }
}























