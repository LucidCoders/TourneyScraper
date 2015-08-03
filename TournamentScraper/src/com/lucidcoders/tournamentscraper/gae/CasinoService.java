package com.lucidcoders.tournamentscraper.gae;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.lucidcoders.tourneyspot.backend.casinoApi.CasinoApi;
import com.lucidcoders.tourneyspot.backend.casinoApi.model.Casino;

public class CasinoService {

    private static CasinoService mCasinoService;
    private CasinoApi mService;

    public static synchronized CasinoService getInstance() throws GeneralSecurityException, IOException {
	if (mCasinoService == null) {
	    mCasinoService = new CasinoService();
	    mCasinoService.build();
	}
	return mCasinoService;
    }

    private void build() throws GeneralSecurityException, IOException {

	CasinoApi.Builder builder = new CasinoApi.Builder(GoogleNetHttpTransport.newTrustedTransport(),
		JacksonFactory.getDefaultInstance(), null).setRootUrl("http://localhost:8080/_ah/api/")
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

    public void updateCasino(Casino casino) throws IOException {
	mService.updateCasino(casino).execute();
    }
    
    public List<Casino> listCasinos() throws IOException {
	return mService.listCasinos().execute().getItems();
    }
    
    public Casino findCasino(String casinoId) throws IOException {
	return mService.findCasino(casinoId).execute();
    }
    
    public void removeAllCasinos() throws IOException {
	mService.removeAllCasinos().execute();
    }
}




















