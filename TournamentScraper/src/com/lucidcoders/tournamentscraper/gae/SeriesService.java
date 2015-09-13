package com.lucidcoders.tournamentscraper.gae;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.lucidcoders.tourneyspot.backend.seriesApi.SeriesApi;
import com.lucidcoders.tourneyspot.backend.seriesApi.model.Series;

public class SeriesService {
    
    private static SeriesService mSeriesService;
    private SeriesApi mSeriesApi;
    
    public static synchronized SeriesService getInstance() throws GeneralSecurityException, IOException {
	if (mSeriesService == null) {
	    mSeriesService = new SeriesService();
	    mSeriesService.build();
	}
	
	return mSeriesService;
    }
    
    private void build() throws GeneralSecurityException, IOException {
	
	SeriesApi.Builder builder = new SeriesApi.Builder(GoogleNetHttpTransport.newTrustedTransport(),
		GsonFactory.getDefaultInstance(), null).setRootUrl("http://localhost.:8080/_ah/api/")
		.setApplicationName("TourneyScraper")
		.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {

		    @Override
		    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
			    throws IOException {
			abstractGoogleClientRequest.setDisableGZipContent(true);
		    }
		});

	mSeriesApi = builder.build();
    }

    public void updateSeries(Series series) throws IOException {
	mSeriesApi.updateSeries(series).execute();
    }
    
    public List<Series> listSeries(String casinoId, DateTime currentDate) throws IOException {
	return mSeriesApi.listSeries().setCasinoId(casinoId).setCurrentDate(currentDate).execute().getItems();
    }
    
    public Series findSeries(String seriesId) throws IOException {
	return mSeriesApi.findSeries(seriesId).execute();
    }
    
    public void removeAllSeries() throws IOException {
	mSeriesApi.removeAllSeries().execute();
    }
}












