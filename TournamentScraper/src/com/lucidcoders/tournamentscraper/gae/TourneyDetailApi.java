package com.lucidcoders.tournamentscraper.gae;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.TourneyDetail;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.TourneyDetails;

public class TourneyDetailApi {

    private static TourneyDetailApi mDetailApi;
    private TourneyDetail service = null;
    
    public static synchronized TourneyDetailApi getInstance() {
	if (mDetailApi == null) {
	    mDetailApi = new TourneyDetailApi();
	    mDetailApi.build();
	}
	
	return mDetailApi;
    }

    private void build() {
	try {
	    TourneyDetail.Builder builder = new TourneyDetail.Builder(GoogleNetHttpTransport.newTrustedTransport(),
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
    
    public List<TourneyDetails> listEvents() {
	try {
	    return service.listEvents().execute().getItems();
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
























