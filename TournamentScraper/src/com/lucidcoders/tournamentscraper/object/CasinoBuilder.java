package com.lucidcoders.tournamentscraper.object;

import java.util.List;

import com.lucidcoders.tournamentscraper.rest.response.AtlasCasinoResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasCasinoResponse.Result;
import com.lucidcoders.tournamentscraper.rest.response.AtlasPokerRoomsResponse;
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
//	    casino.setImage(image); // TODO setImage from Url
	}
	
	return casino;
    }
}
