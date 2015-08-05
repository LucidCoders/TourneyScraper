package com.lucidcoders.tournamentscraper.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.lucidcoders.tournamentscraper.rest.response.AtlasErrorResponse;

public class ImportIoRequest {

    private RequestConfig requestConfig;
    private final int TIMEOUT = 10 * 1000;

    private final String USER_AGENT = "Mozilla/5.0";
    private final String USER_ID = "9edd0fab-f45b-4fb4-b6f6-8418d65b44fe";
    private final String API_KEY = "9edd0fabf45b4fb4b6f68418d65b44feda925482c0aa538685fe64ba2428e1908652f9b260beec9e08b81118d90cdb4b583229745e0de1f372998577a896c18c2768cce833339466010da98ccbc7ce1c";
    
    private String mTargetUrl;
    private String mUrl;
    private String mResult;
    
    private AtlasErrorResponse mAtlasErrorResponse;
    private boolean mIsAtlasError = true;

    public ImportIoRequest(String targetUrl) {
	mTargetUrl = targetUrl;
	requestConfig = RequestConfig.custom().setConnectTimeout(TIMEOUT).build();
    }

    public HttpResponse queryGet(Extractor extractor) throws URISyntaxException, IOException {

	mUrl = "https://api.import.io/store/data/" + extractor.getId() + "/_query";

	URIBuilder builder = new URIBuilder(mUrl);
	builder.addParameter("input/webpage/url", mTargetUrl);
	builder.addParameter("_user", USER_ID);
	builder.addParameter("_apikey", API_KEY);
	
	URI urlWithParams = builder.build();
	HttpGet request = new HttpGet(urlWithParams);
	request.addHeader("User-Agent", USER_AGENT);
	request.addHeader("content-type", "application/json");

	System.out.println("\nSending 'GET' request to URL : " + urlWithParams);
	return executeGet(request);
    }

    public HttpResponse magicGet() throws URISyntaxException, IOException {

	mUrl = "https://api.import.io/store/connector/_magic";

	URIBuilder builder = new URIBuilder(mUrl);
	builder.addParameter("url", mTargetUrl);
	builder.addParameter("_user", USER_ID);
	builder.addParameter("_apikey", API_KEY);

	URI urlWithParams = builder.build();
	HttpGet request = new HttpGet(urlWithParams);
	request.addHeader("User-Agent", USER_AGENT);
	request.addHeader("content-type", "application/json");

	System.out.println("\nSending 'GET' request to URL : " + urlWithParams);
	return executeGet(request);
    }

    private HttpResponse executeGet(HttpGet request) throws IOException {

	HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
	HttpResponse response = client.execute(request);

	mResult = EntityUtils.toString(response.getEntity(), "UTF-8");
//	System.out.println(mResult);
	
	try {
	    mAtlasErrorResponse = new Gson().fromJson(mResult, AtlasErrorResponse.class);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	mIsAtlasError = mAtlasErrorResponse.getErrorType() != null;

	return response;
    }

    public void sendPost() throws Exception {

	HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
	HttpPost post = new HttpPost(mUrl);

	// add header
	post.setHeader("User-Agent", USER_AGENT);

	List<NameValuePair> mUrlParameters = new ArrayList<NameValuePair>();
	mUrlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
	mUrlParameters.add(new BasicNameValuePair("cn", ""));
	mUrlParameters.add(new BasicNameValuePair("locale", ""));
	mUrlParameters.add(new BasicNameValuePair("caller", ""));
	mUrlParameters.add(new BasicNameValuePair("num", "12345"));

	post.setEntity(new UrlEncodedFormEntity(mUrlParameters));

	HttpResponse response = client.execute(post);
	System.out.println("\nSending 'POST' request to URL : " + mUrl);
	System.out.println("Post parameters : " + post.getEntity());
	System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

	mResult = EntityUtils.toString(response.getEntity(), "UTF-8");

	System.out.println(mResult);
    }

    public String getResult() {
	return mResult;
    }
    
    public AtlasErrorResponse getAtlasError() {
	return mAtlasErrorResponse;
    }
    
    public boolean isAtlasError() {
	return mIsAtlasError;
    }
}