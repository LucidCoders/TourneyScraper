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
    private TourneyDetailApi mService = null;

    public static synchronized TourneyDetailService getInstance() throws GeneralSecurityException, IOException {
	if (mDetailApi == null) {
	    mDetailApi = new TourneyDetailService();
	    mDetailApi.build();
	}

	return mDetailApi;
    }

    private void build() throws GeneralSecurityException, IOException {

	TourneyDetailApi.Builder builder = new TourneyDetailApi.Builder(GoogleNetHttpTransport.newTrustedTransport(),
		JacksonFactory.getDefaultInstance(), null).setRootUrl("https://tourneyspot.appspot.com/_ah/api/")
		.setApplicationName("TourneyScraper")
		.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {

		    @Override
		    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
			    throws IOException {
			abstractGoogleClientRequest.setDisableGZipContent(true);
		    }
		});

	mService = builder.build();
    }

    public List<TourneyDetails> listEvents(String casinoId, DateTime eventDate) throws IOException {
	return mService.listEvents().setCasinoId(casinoId).setEventDate(eventDate).execute().getItems();
    }
    
    public List<TourneyDetails> listSeriesEvents(String seriesId, DateTime eventDate) throws IOException {
	return mService.listSeriesEvents().setSeriesId(seriesId).setEventDate(eventDate).execute().getItems();
    }

    public void updateEvent(TourneyDetails tourneyDetails) throws IOException {
	mService.updateEvent(tourneyDetails).execute();
    }

    public void removeAllEvents() throws IOException {
	mService.removeAllEvents().execute();
    }
}









