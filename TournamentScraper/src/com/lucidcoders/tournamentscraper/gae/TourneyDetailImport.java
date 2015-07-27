package com.lucidcoders.tournamentscraper.gae;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.TourneyDetails;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.TourneyDetail;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

public class TourneyDetailImport {

    private TourneyDetail service = null;
    private TourneyDetails mTourneyDetails;

    public TourneyDetailImport(TourneyDetails tourneyDetails) {
	mTourneyDetails = tourneyDetails;
    }
    
    public void execute() {
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
	    
	    service.updateEvent(mTourneyDetails).execute();	    
	    
	} catch (GeneralSecurityException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
























