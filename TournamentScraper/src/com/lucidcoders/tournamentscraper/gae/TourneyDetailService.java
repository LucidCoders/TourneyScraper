package com.lucidcoders.tournamentscraper.gae;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.lucidcoders.tourneyspot.backend.tourneyDetailApi.TourneyDetailApi;
import com.lucidcoders.tourneyspot.backend.tourneyDetailApi.model.TourneyDetails;

public class TourneyDetailService {

    private static TourneyDetailService mDetailApi;
    private TourneyDetailApi service = null;
    
    public static synchronized TourneyDetailService getInstance() {
	if (mDetailApi == null) {
	    mDetailApi = new TourneyDetailService();
	    mDetailApi.build();
	}
	
	return mDetailApi;
    }

    private void build() {
	try {
	    TourneyDetailApi.Builder builder = new TourneyDetailApi.Builder(GoogleNetHttpTransport.newTrustedTransport(),
	    	JacksonFactory.getDefaultInstance(), null)
	    .setRootUrl("http://localhost:8080/_ah/api/")
	    .setApplicationName("TourneyScraper")
	    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
	        
	        @Override
	        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
	            abstractGoogleClientRequest.setDisableGZipContent(true);
	        }
	    });
	    
	    service = builder.build();
	    
	} catch (GeneralSecurityException | IOException e) {
	    e.printStackTrace();
	}
    }
    
    public List<TourneyDetails> listEvents(String casinoId, DateTime eventDate) {
	try {
//	    return service.listEvents().execute().getItems();
	    return service.listEvents().setCasinoId(casinoId).set("eventDate", eventDate).execute().getItems();
	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
    }
    
    public void updateEvent(TourneyDetails tourneyDetails) {
	try {
	    System.out.println("*** Performing Insert : " + tourneyDetails.getAtlasId() + " ***");
	    service.updateEvent(tourneyDetails).execute();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public void removeAllEvents() {
	try {
	    service.removeAllEvents().execute();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
























